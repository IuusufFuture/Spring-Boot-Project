package com.example.finalporject.models.dto;

import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.entities.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {
    Long id;
    double price;
    Date startDate;
    Date endDate;
    ProductDto productId;
}
