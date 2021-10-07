package com.example.finalporject.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProvideOperationDtoResponse {
    double totalPrice;
    Long operationId;
    List<ProvideOperationExtraDto> provideOperationExtraDtos;
}
