package com.example.finalporject.services;

import com.example.finalporject.models.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> save(String token, ProductDto productDto);

    ResponseEntity<?> getAll(String token);

    ResponseEntity<?> delete(String token, Long id, boolean active);

    ResponseEntity<?> getByProductName(String token, String productName);

    ProductDto getByBarcode(String productName);
}
