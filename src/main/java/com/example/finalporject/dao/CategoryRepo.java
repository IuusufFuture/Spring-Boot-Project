package com.example.finalporject.dao;

import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
}
