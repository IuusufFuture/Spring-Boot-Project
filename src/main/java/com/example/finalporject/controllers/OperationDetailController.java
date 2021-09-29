package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.OperationDetailDto;
import com.example.finalporject.services.OperationDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/operationDetail")
public class OperationDetailController {
    private OperationDetailService operationDetailService;

    public OperationDetailController(OperationDetailService operationDetailService) {
        this.operationDetailService = operationDetailService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createOperDetail(@RequestHeader String token, @RequestParam OperationDetailDto operationDetailDto) {
        return operationDetailService.save(token, operationDetailDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        return operationDetailService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOperDetail(@RequestHeader String token, @RequestParam OperationDetailDto operationDetailDto) {
        return operationDetailService.save(token, operationDetailDto);
    }
}
