package com.example.finalporject.models.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailDto {
    Long id;
    ProductDto productId;
    OperationDto operationId;
    int amount;
    double price;
}
