package com.example.finalporject.services.impl;

import com.example.finalporject.dao.ProductRepo;
import com.example.finalporject.mappers.ProductMapper;
import com.example.finalporject.models.dto.ProductDto;
import com.example.finalporject.models.entities.Product;
import com.example.finalporject.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<?> save(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        product = productRepo.save(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Product> productList = productRepo.findAll();
        return ResponseEntity.ok(productList.stream().map(ProductMapper.INSTANCE::toProductDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> delete(Long id, boolean active) {
        Product product = productRepo.findById(id).get();
        product.setActive(active);
        product = productRepo.save(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }
}
