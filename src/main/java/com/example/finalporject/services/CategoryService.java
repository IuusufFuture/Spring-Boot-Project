package com.example.finalporject.services;

import com.example.finalporject.models.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto);
    ResponseEntity<?> getAll(String token);
    ResponseEntity<?> delete(String token, Long id, boolean active);
    ResponseEntity<?> getByName(String token, String categoryName);
}
