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
    public ResponseEntity<?> saveCategory(@RequestHeader String token, @RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(token, categoryDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory(@RequestHeader String token) {
        return categoryService.getAll(token);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestHeader String token, @RequestParam String categoryName) {
        return categoryService.getByName(token, categoryName);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<?> updateCategory(@RequestHeader String token, @RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(token, categoryDto);
    }

    @PutMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@RequestHeader String token, @RequestParam Long id, @RequestParam boolean active) {
        return categoryService.delete(token, id, active);
    }
}
