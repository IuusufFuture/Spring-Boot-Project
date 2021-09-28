package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.ProductDto;
import com.example.finalporject.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createProduct(@RequestParam ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return productService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id, @RequestParam boolean active) {
        return productService.delete(id, active);
    }
}
