package com.example.finalporject.services.impl;

import com.example.finalporject.dao.CodeRepo;
import com.example.finalporject.mappers.CodeMapper;
import com.example.finalporject.mappers.UserMapper;
import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.dto.UserDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import com.example.finalporject.services.CodeService;
import com.example.finalporject.services.SendEmailService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepo codeRepo;
    private SendEmailService sendEmailService;

    public CodeServiceImpl(CodeRepo codeRepo, SendEmailService sendEmailService) {
        this.codeRepo = codeRepo;
        this.sendEmailService = sendEmailService;
    }

    @Override
    public void saveCode(CodeDto codeDto) {
        codeRepo.save(CodeMapper.INSTANCE.toCode(codeDto));
    }

    @Override
    public Code getByCode(User login) {
        return codeRepo.findByUserIdAndCodeStatus(login, CodeStatus.NEW);
    }

    @Override
    public void sendCode(UserDto toUserDto) {
        Code lastCode = codeRepo.findByUserIdAndCodeStatus(UserMapper.INSTANCE.toUser(toUserDto), CodeStatus.NEW);
        if(Objects.nonNull(lastCode)) {
            lastCode.setCodeStatus(CodeStatus.CANCELLED);
            codeRepo.save(lastCode);
        }

        int randomCode = (int) (Math.random() * 9000) + 1000;
        String hashedCode = BCrypt.hashpw(Integer.toString(randomCode), BCrypt.gensalt());

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MINUTE, 3);

        Code actualCode = new Code();
        actualCode.setCode(hashedCode);
        actualCode.setCodeStatus(CodeStatus.NEW);
        actualCode.setEndDate(endDate.getTime());
        actualCode.setUserId(UserMapper.INSTANCE.toUser(toUserDto));

        codeRepo.save(actualCode);

        sendEmailService.sendSimpleMessage(toUserDto.getEmail(), "Code verification" ,Integer.toString(randomCode));
    }
}
