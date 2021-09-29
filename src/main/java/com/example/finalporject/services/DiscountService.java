package com.example.finalporject.services;

import com.example.finalporject.models.dto.DiscountDto;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> saveDiscount(String token, DiscountDto discountDto);

    ResponseEntity<?> getAll(String token);
}
