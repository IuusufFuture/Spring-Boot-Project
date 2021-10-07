package com.example.finalporject.services;

import com.example.finalporject.models.dto.PriceDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> savePrice(String token, PriceDto priceDto);
    ResponseEntity<?> getAll(String token);

    ResponseEntity<?> findPrice(String token, Long id);
    double getPrice(Long id);
}
