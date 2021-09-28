package com.example.finalporject.services;

import com.example.finalporject.models.dto.OperationDetailDto;
import org.springframework.http.ResponseEntity;

public interface OperationDetailService {
    ResponseEntity<?> save(OperationDetailDto operationDetailDto);

    ResponseEntity<?> getAll();
}
