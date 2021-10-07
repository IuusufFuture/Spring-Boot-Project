package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.ProvideOperationDto;
import com.example.finalporject.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
public class OperationController {

    private OperationService operationService;

    public OperationController(OperationService operService) {
        this.operationService = operService;
    }

    @PostMapping("/provide")
    public ResponseEntity<?> provideOperation(@RequestHeader String token, @RequestBody List<ProvideOperationDto> provideOperationDtoList) {
        return operationService.provide(token, provideOperationDtoList);
    }

    @GetMapping("/pay")
    public ResponseEntity<?> payOperation(@RequestHeader String token, @RequestParam Long operationId, @RequestParam double cash) {
        return operationService.pay(token, operationId, cash);
    }
}
