package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.OperationDto;
import com.example.finalporject.models.entities.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDto toOperationDto(Operation operation);
    Operation toOperation(OperationDto operationDto);
}
