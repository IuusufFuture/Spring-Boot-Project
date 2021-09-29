package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.ProductDto;
import com.example.finalporject.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestHeader String token, @RequestParam ProductDto productDto) {
        return productService.save(token, productDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        return productService.getAll(token);
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(@RequestHeader String token, @RequestParam String productName) {
        return productService.getByProductName(token, productName);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestHeader String token, @RequestParam ProductDto productDto) {
        return productService.save(token, productDto);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestHeader String token, @RequestParam Long id, @RequestParam boolean active) {
        return productService.delete(token, id, active);
    }
}
