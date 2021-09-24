package com.example.finalporject.services.impl;

import com.example.finalporject.dao.UserRepo;
import com.example.finalporject.mappers.UserMapper;
import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.models.responses.OkRepsonse;
import com.example.finalporject.services.CodeService;
import com.example.finalporject.services.SendEmailService;
import com.example.finalporject.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CodeService codeService;
    @Autowired
    private SendEmailService sendEmailService;

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
            codeService.saveCode(lastCode);
        }

        Code actualCode = new Code();
        actualCode.setCode(hashedCode);
        actualCode.setCodeStatus(CodeStatus.NEW);
        actualCode.setEndDate(endDate.getTime());
        actualCode.setUserId(userFoundLogin);
        codeService.saveCode(actualCode);

        sendEmailService.sendSimpleMessage(userFoundLogin.getEmail(), "Code verification" ,Integer.toString(randomCode));

        return ResponseEntity.ok(UserMapper.INSTANCE.toUserEmailDto(userFoundLogin));
    }

    @Value("${jwtSecret}")
    private String secretKey;

    @Override
    public ResponseEntity<?> getToken(String login, String code) {
        User loginFound = userRepo.findByLogin(login);
        if(Objects.isNull(loginFound)) {
            return new ResponseEntity<>(new ErrorResponse("No login found in the database"), HttpStatus.NOT_FOUND);
        }

        Code userCode = codeService.getByCode(loginFound);

        // It takes input (param) and hashes
        String hashedCode = BCrypt.hashpw(code, BCrypt.gensalt());

        // Then we take hashedCode that person entered and compare it to DataBase code
        if(!BCrypt.checkpw(userCode.getCode(), hashedCode)) {
            return new ResponseEntity<>(new ErrorResponse("Did not pass verification. Wrong code input"), HttpStatus.CONFLICT);
        }

        Calendar tokenLifeSpan = Calendar.getInstance();
        tokenLifeSpan.add(Calendar.MINUTE, 5);

        String token = Jwts.builder()
                .claim("login", login)
                .setExpiration(tokenLifeSpan.getTime())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return ResponseEntity.ok(token);

        /*
        if 3 min passes
            -> code gets CANCELLED 'CodeStatus' inside of Code.class
            -> return: time has passed, please try to create a new verification
        if 3 wrong inputs
            -> save each input's date when it was entered
            -> code gets FAILED 'CodeStatus.class'
            -> return: didn't pass verification
        if true input
            -> turn success parameter in Request.class to true if code == input
            -> code gets APPROVED 'CodeStatus.class'
            -< return:
        */

    }
}
