package com.example.finalporject.services.impl;

import com.example.finalporject.dao.ProductRepo;
import com.example.finalporject.mappers.CategoryMapper;
import com.example.finalporject.mappers.ProductMapper;
import com.example.finalporject.models.dto.ProductDto;
import com.example.finalporject.models.entities.Category;
import com.example.finalporject.models.entities.Product;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.ProductService;
import com.example.finalporject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;
    private UserService userService;

    public ProductServiceImpl(ProductRepo productRepo, UserService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, ProductDto productDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Product product = ProductMapper.INSTANCE.toProduct(productDto);

        if(Objects.isNull(productRepo.findByName(product.getName()))) {
            product = productRepo.save(product);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Product with that name already exists"), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Product> productList = productRepo.findAll();

        if(Objects.isNull(productList)) {
            return new ResponseEntity<>(new ErrorResponse("Product not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productList.stream().map(ProductMapper.INSTANCE::toProductDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> delete(String token, Long id, boolean active) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Product product = productRepo.findById(id).get();
        product.setActive(active);
        product = productRepo.save(product);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }

    @Override
    public ResponseEntity<?> getByProductName(String token, String productName) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Product product = productRepo.findByName(productName);

        if(Objects.isNull(product)) {
            return new ResponseEntity<>(new ErrorResponse("Product by that name was not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }
}
