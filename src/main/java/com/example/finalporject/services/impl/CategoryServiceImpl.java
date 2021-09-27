package com.example.finalporject.services.impl;

import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public ResponseEntity<?> createCategory(CategoryDto categoryDto) {
        return null;
    }
}
