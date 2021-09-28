package com.example.finalporject.services;

import com.example.finalporject.models.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> save(ProductDto productDto);

    ResponseEntity<?> getAll();

    ResponseEntity<?> delete(Long id, boolean active);
}
