package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestParam CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
        return categoryService.getAll();
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestParam CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestParam Long id, @RequestParam boolean active) {
        return categoryService.delete(id, active);
    }
}
