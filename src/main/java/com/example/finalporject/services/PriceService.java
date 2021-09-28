package com.example.finalporject.services;

import com.example.finalporject.models.dto.PriceDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> save(PriceDto priceDto);
    ResponseEntity<?> getAll();
}
