package com.example.finalporject.services.impl;

import com.example.finalporject.dao.OperRepo;
import com.example.finalporject.mappers.OperationMapper;
import com.example.finalporject.models.dto.OperationDto;
import com.example.finalporject.models.entities.Operation;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.OperationService;
import com.example.finalporject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {
    private OperRepo operRepo;
    private UserService userService;

    public OperationServiceImpl(OperRepo operRepo, UserService userService) {
        this.operRepo = operRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, OperationDto operationDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Operation operation = OperationMapper.INSTANCE.toOperation(operationDto);
        operation = operRepo.save(operation);
        return ResponseEntity.ok(OperationMapper.INSTANCE.toOperationDto(operation));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Operation> operationList = operRepo.findAll();

        if(Objects.isNull(operationList)) {
            return new ResponseEntity<>(new ErrorResponse("Operation not found"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(operationList.stream().map(OperationMapper.INSTANCE::toOperationDto).collect(Collectors.toList()));
    }
}
