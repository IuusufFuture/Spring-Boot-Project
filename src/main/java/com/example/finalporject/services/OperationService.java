package com.example.finalporject.services;

import com.example.finalporject.models.dto.OperationDto;
import org.springframework.http.ResponseEntity;

public interface OperationService {
    ResponseEntity<?> save(OperationDto operationDto);
    ResponseEntity<?> getAll();
}
