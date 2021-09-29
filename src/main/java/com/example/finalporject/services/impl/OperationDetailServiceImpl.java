package com.example.finalporject.services.impl;

import com.example.finalporject.dao.OperDetailRepo;
import com.example.finalporject.mappers.OperationDetailMapper;
import com.example.finalporject.models.dto.OperationDetailDto;
import com.example.finalporject.models.entities.OperationDetail;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.OperationDetailService;
import com.example.finalporject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OperationDetailServiceImpl implements OperationDetailService {
    private OperDetailRepo operDetailRepo;
    private UserService userService;

    public OperationDetailServiceImpl(OperDetailRepo operDetailRepo, UserService userService) {
        this.operDetailRepo = operDetailRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, OperationDetailDto operationDetailDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        OperationDetail operationDetail = OperationDetailMapper.INSTANCE.toOperationDetail(operationDetailDto);
        operationDetail = operDetailRepo.save(operationDetail);
        return ResponseEntity.ok(OperationDetailMapper.INSTANCE.toOperationDetailDto(operationDetail));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<OperationDetail> operationDetails = operDetailRepo.findAll();
        if(Objects.isNull(operationDetails)) {
            return new ResponseEntity<>(new ErrorResponse("Operation Detail not found"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(operationDetails.stream().map(OperationDetailMapper.INSTANCE::toOperationDetailDto).collect(Collectors.toList()));
    }
}
