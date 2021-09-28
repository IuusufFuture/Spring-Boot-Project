package com.example.finalporject.services.impl;

import com.example.finalporject.dao.OperDetailRepo;
import com.example.finalporject.mappers.OperationDetailMapper;
import com.example.finalporject.models.dto.OperationDetailDto;
import com.example.finalporject.models.entities.OperationDetail;
import com.example.finalporject.services.OperationDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationDetailServiceImpl implements OperationDetailService {
    private OperDetailRepo operDetailRepo;

    public OperationDetailServiceImpl(OperDetailRepo operDetailRepo) {
        this.operDetailRepo = operDetailRepo;
    }

    @Override
    public ResponseEntity<?> save(OperationDetailDto operationDetailDto) {
        OperationDetail operationDetail = OperationDetailMapper.INSTANCE.toOperationDetail(operationDetailDto);
        operationDetail = operDetailRepo.save(operationDetail);
        return ResponseEntity.ok(OperationDetailMapper.INSTANCE.toOperationDetailDto(operationDetail));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<OperationDetail> operationDetails = operDetailRepo.findAll();
        return ResponseEntity.ok(operationDetails.stream().map(OperationDetailMapper.INSTANCE::toOperationDetailDto).collect(Collectors.toList()));
    }
}
