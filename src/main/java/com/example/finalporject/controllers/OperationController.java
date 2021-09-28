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
    public ResponseEntity<?> createOperation(@RequestParam OperationDto operationDto) {
        return operService.save(operationDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return operService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOperation(@RequestParam OperationDto operationDto) {
        return operService.save(operationDto);
    }

//    @PutMapping("/delete")
//    public ResponseEntity<?> {
//    }
}
