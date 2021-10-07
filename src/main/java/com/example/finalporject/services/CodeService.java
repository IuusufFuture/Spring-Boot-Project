package com.example.finalporject.services;

import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.dto.UserDto;
import com.example.finalporject.models.entities.Code;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.enums.CodeStatus;
import org.springframework.http.ResponseEntity;

public interface CodeService {
    void saveCode(CodeDto codeDto);
    Code getByCode(User login);

    void sendCode(UserDto toUserDto);
}
