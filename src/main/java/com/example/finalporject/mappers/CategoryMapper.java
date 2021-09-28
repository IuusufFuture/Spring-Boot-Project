package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.CategoryDto;
import com.example.finalporject.models.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);
}
