package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestParam CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }
}
