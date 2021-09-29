package com.example.finalporject.services;

import com.example.finalporject.models.dto.OperationDto;
import org.springframework.http.ResponseEntity;

public interface OperationService {
    ResponseEntity<?> save(String token, OperationDto operationDto);
    ResponseEntity<?> getAll(String token);
}
