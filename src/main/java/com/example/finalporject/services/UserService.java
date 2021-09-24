package com.example.finalporject.services;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> sendCode(String login);

    ResponseEntity<?> getToken(String login, String code);
}
