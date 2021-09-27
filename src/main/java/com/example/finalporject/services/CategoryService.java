package com.example.finalporject.services;

import com.example.finalporject.models.dto.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<?> createCategory(CategoryDto categoryDto);
}
