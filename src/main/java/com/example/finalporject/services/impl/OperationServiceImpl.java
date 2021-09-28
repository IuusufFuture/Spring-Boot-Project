package com.example.finalporject.services.impl;

import com.example.finalporject.dao.OperRepo;
import com.example.finalporject.mappers.OperationMapper;
import com.example.finalporject.models.dto.OperationDto;
import com.example.finalporject.models.entities.Operation;
import com.example.finalporject.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {
    private OperRepo operRepo;

    public OperationServiceImpl(OperRepo operRepo) {
        this.operRepo = operRepo;
    }

    @Override
    public ResponseEntity<?> save(OperationDto operationDto) {
        Operation operation = OperationMapper.INSTANCE.toOperation(operationDto);
        operation = operRepo.save(operation);
        return ResponseEntity.ok(OperationMapper.INSTANCE.toOperationDto(operation));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Operation> operationList = operRepo.findAll();
        return ResponseEntity.ok(operationList.stream().map(OperationMapper.INSTANCE::toOperationDto).collect(Collectors.toList()));
    }
}
