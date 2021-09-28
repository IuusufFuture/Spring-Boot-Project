package com.example.finalporject.services;

import com.example.finalporject.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> getToken(String login, String code);

    ResponseEntity<?> verifyUser(String token);

    ResponseEntity<?> save(UserDto userDto);
}
