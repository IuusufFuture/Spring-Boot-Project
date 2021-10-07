package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.ProductDto;
import com.example.finalporject.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}