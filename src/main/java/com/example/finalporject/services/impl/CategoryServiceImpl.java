package com.example.finalporject.services.impl;

import com.example.finalporject.dao.CategoryRepo;
import com.example.finalporject.mappers.CategoryMapper;
import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.models.entities.Category;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.CategoryService;
import com.example.finalporject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    private UserService userService;

    public CategoryServiceImpl(CategoryRepo categoryRepo, UserService userService) {
        this.categoryRepo = categoryRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveCategory(String token, CategoryDto categoryDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);
        if(Objects.isNull(categoryRepo.findByName(category.getName()))) {
            category = categoryRepo.save(category);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Category with that name already exists"), HttpStatus.OK);
        }

        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Category> categoryList = categoryRepo.findAll();

        if(Objects.isNull(categoryList)) {
            return new ResponseEntity<>(new ErrorResponse("Category not found"), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(categoryList.stream().map(CategoryMapper.INSTANCE::toCategoryDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> getByName(String token, String categoryName) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Category category = categoryRepo.findByName(categoryName);

        if(Objects.isNull(category)) {
            return new ResponseEntity<>(new ErrorResponse("Category by that name was not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }

    @Override
    public ResponseEntity<?> delete(String token, Long id, boolean active) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Category category = categoryRepo.findById(id).get();
        category.setActive(active);
        category = categoryRepo.save(category);
        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));
    }


}
