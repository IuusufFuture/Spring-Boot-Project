package com.example.finalporject.services;

import com.example.finalporject.models.dto.OperationDto;
import com.example.finalporject.models.dto.ProvideOperationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    ResponseEntity<?> provide(String token, List<ProvideOperationDto> provideOperationDtoList);
    ResponseEntity<?> pay(String token, Long operationId, double cash);
}
