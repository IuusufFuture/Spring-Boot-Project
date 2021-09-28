package com.example.finalporject.services.impl;

import com.example.finalporject.dao.UserRepo;
import com.example.finalporject.mappers.CodeMapper;
import com.example.finalporject.mappers.UserMapper;
import com.example.finalporject.models.dto.UserDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.Request;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.CodeService;
import com.example.finalporject.services.RequestService;
import com.example.finalporject.services.SendEmailService;
import com.example.finalporject.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private SendEmailService sendEmailService;
    private CodeService codeService;
    private RequestService requestService;
    private Formatter formatter;

    public UserServiceImpl(UserRepo userRepo, SendEmailService sendEmailService, CodeService codeService, RequestService requestService) {
        this.userRepo = userRepo;
        this.sendEmailService = sendEmailService;
        this.codeService = codeService;
        this.requestService = requestService;
    }

    @Override
    public ResponseEntity<?> sendCode(String login) {
        User userFoundLogin = userRepo.findByLogin(login);
        if(Objects.isNull(userFoundLogin)) {
            return new ResponseEntity<>(new ErrorResponse("Login not found"), HttpStatus.NOT_FOUND);
        }
        int randomCode = codeService.codeRandom();
        String hashedCode = BCrypt.hashpw(Integer.toString(randomCode), BCrypt.gensalt());

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MINUTE, 3);

        Code lastCode = codeService.getByCode(userFoundLogin);
        if(Objects.nonNull(lastCode)) {
            lastCode.setCodeStatus(CodeStatus.CANCELLED);
            codeService.saveCode(CodeMapper.INSTANCE.toCodeDto(lastCode));
        }

        Code actualCode = new Code();
        actualCode.setCode(hashedCode);
        actualCode.setCodeStatus(CodeStatus.NEW);
        actualCode.setEndDate(endDate.getTime());
        actualCode.setUserId(userFoundLogin);
        codeService.saveCode(CodeMapper.INSTANCE.toCodeDto(actualCode));

        sendEmailService.sendSimpleMessage(userFoundLogin.getEmail(), "Code verification" ,Integer.toString(randomCode));

        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(userFoundLogin));
    }

    @Value("${jwtSecret}")
    private String secretKey;

    @Override
    public ResponseEntity<?> getToken(String login, String code) {
        User loginFound = userRepo.findByLogin(login);
        if(Objects.isNull(loginFound)) {
            return new ResponseEntity<>(new ErrorResponse("No login found in the database"), HttpStatus.NOT_FOUND);
        }

        // It already gets hashed in 'sendCode' method
        Code lastHashedCode = codeService.getByCode(loginFound);
        if(Objects.isNull(lastHashedCode)) {
            return new ResponseEntity<>(new ErrorResponse("Please create a NEW verification code."), HttpStatus.NOT_FOUND);
        }

        System.out.println(loginFound.getBlockDate().before(new Date()));
        if(loginFound.getBlockDate().before(new Date()) || Objects.isNull(loginFound.getBlockDate() )) {
            Request request = new Request();
            if(!BCrypt.checkpw(code, lastHashedCode.getCode())) {
                formatter = new Formatter();
                int maxInputs = 4;
                request.setAddDate(new Date());
                request.setSuccess(false);
                request.setCodeId(lastHashedCode);
                requestService.saveRequest(request);

                // Count how many times code was entered
                int countInputs = requestService.countByCodeId(request.getCodeId());

                // Block user for an hour
                if(countInputs == maxInputs) {
                    Calendar blockedTime = Calendar.getInstance();
                    blockedTime.add(Calendar.HOUR, 1);
                    loginFound.setBlockDate(blockedTime.getTime());
                    lastHashedCode.setCodeStatus(CodeStatus.FAILED);
                    codeService.saveCode(CodeMapper.INSTANCE.toCodeDto(lastHashedCode));

                    if(Objects.isNull(loginFound.getBlockDate())) {
                        return new ResponseEntity<>(new ErrorResponse("Blocked Date is null"), HttpStatus.CONFLICT);
                    }
                    formatter.format("%tl:%tM", loginFound.getBlockDate().getTime(), loginFound.getBlockDate().getTime());
                    userRepo.save(loginFound);
                    return new ResponseEntity<>(new ErrorResponse("You have tried 3 times and have been blocked for an hour. Please, try again later at " + formatter), HttpStatus.CONFLICT);
                } else if(countInputs > maxInputs) {
                    return new ResponseEntity<>(new ErrorResponse("You have been blocked, Please, try later."), HttpStatus.CONFLICT);
                }
                return new ResponseEntity<>(new ErrorResponse("Did not pass verification. Wrong code input."), HttpStatus.NOT_FOUND);
            }

            if(lastHashedCode.getEndDate().before(new Date())) {
                lastHashedCode.setCodeStatus(CodeStatus.CANCELLED);
                codeService.saveCode(CodeMapper.INSTANCE.toCodeDto(lastHashedCode));
                return new ResponseEntity<>(new ErrorResponse("The code has gotten cancelled, 3 minutes passed. Please try to create a new verification code."), HttpStatus.CONFLICT);
            }

            Calendar tokenLifeSpan = Calendar.getInstance();
            tokenLifeSpan.add(Calendar.MINUTE, 5);

            String token = Jwts.builder()
                    .claim("login", login)
                    .setExpiration(tokenLifeSpan.getTime())
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();

            request.setSuccess(true);
            request.setAddDate(new Date());
            request.setCodeId(lastHashedCode);
            requestService.saveRequest(request);
            // He can use this code only ONCE! If he tries to use the same code twice or more, it won't work
            lastHashedCode.setCodeStatus(CodeStatus.APPROVED);
            codeService.saveCode(CodeMapper.INSTANCE.toCodeDto(lastHashedCode));
            return ResponseEntity.ok(token);
        } else {
            return new ResponseEntity<>(new ErrorResponse("You have been blocked for 1 hour. "), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> verifyUser(String token) {
        try {
            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return ResponseEntity.ok(jwt.getBody().get("login") );
        } catch (ExpiredJwtException expEx) {
            return new ResponseEntity<>("JWT has gotten expired", HttpStatus.CONFLICT);
        } catch (UnsupportedJwtException uns) {
            return new ResponseEntity<>("Entered format/configuration does not match the format expected by the application", HttpStatus.CONFLICT);
        } catch (MalformedJwtException expEx) {
            return new ResponseEntity<>("JWT was not correctly constructed", HttpStatus.CONFLICT);
        } catch (SignatureException expEx) {
            return new ResponseEntity<>("Calculating a signature or verifying an existing signature of JWT failed", HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> save(UserDto userDto) {
        User user = UserMapper.INSTANCE.toUser(userDto);
        if(Objects.isNull(userRepo.findByLogin(user.getLogin()))){
            user = userRepo.save(user);
        } else {
            return new ResponseEntity<>(new ErrorResponse("User with that login already exists"), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(user));
    }
}