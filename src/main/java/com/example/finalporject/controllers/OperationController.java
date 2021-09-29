package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.OperationDto;
import com.example.finalporject.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operation")
public class OperationController {
    private OperationService operService;

    public OperationController(OperationService operService) {
        this.operService = operService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createOperation(@RequestHeader String token, @RequestParam OperationDto operationDto) {
        return operService.save(token, operationDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        return operService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOperation(@RequestHeader String token, @RequestParam OperationDto operationDto) {
        return operService.save(token, operationDto);
    }
}
