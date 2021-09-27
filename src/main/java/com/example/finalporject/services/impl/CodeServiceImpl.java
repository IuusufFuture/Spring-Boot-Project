package com.example.finalporject.services.impl;

import com.example.finalporject.dao.CodeRepo;
import com.example.finalporject.mappers.CodeMapper;
import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import com.example.finalporject.services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepo codeRepo;

    @Override
    public int codeRandom() {
        Random random = new Random();
        return random.nextInt(9999 - 1000 + 1) + 1000;
    }

    @Override
    public CodeDto saveCode(CodeDto codeParam) {
        Code code = CodeMapper.INSTANCE.toCode(codeParam);
        if(!codeRepo.existsById(code.getId())) {
            code = codeRepo.save(code);
        }
        return CodeMapper.INSTANCE.toCodeDto(code);
    }

    @Override
    public Code getByCode(User login) {
        return codeRepo.findByUserIdAndCodeStatus(login, CodeStatus.NEW);
    }
}
