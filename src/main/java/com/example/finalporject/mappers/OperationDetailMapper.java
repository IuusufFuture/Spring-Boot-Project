package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.OperationDetailDto;
import com.example.finalporject.models.entities.OperationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationDetailMapper {
    OperationDetailMapper INSTANCE = Mappers.getMapper(OperationDetailMapper.class);

    OperationDetailDto toOperationDetailDto(OperationDetail operationDetail);
    OperationDetail toOperationDetail(OperationDetailDto operationDetailDto);
}
