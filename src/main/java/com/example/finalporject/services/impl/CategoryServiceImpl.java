package com.example.finalporject.services.impl;

import com.example.finalporject.dao.CategoryRepo;
import com.example.finalporject.mappers.CategoryMapper;
import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.models.entities.Category;
import com.example.finalporject.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ResponseEntity<?> saveCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);
        category = categoryRepo.save(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Category> categoryList = categoryRepo.findAll();
        return ResponseEntity.ok(categoryList.stream().map(CategoryMapper.INSTANCE::toCategoryDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> delete(Long id, boolean active) {
        Category category = categoryRepo.findById(id).get();
        category.setActive(active);
        category = categoryRepo.save(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }
}
