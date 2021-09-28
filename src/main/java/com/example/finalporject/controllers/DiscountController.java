package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.DiscountDto;
import com.example.finalporject.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    private DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createDiscount(@RequestParam DiscountDto discountDto) {
        return discountService.saveDiscount(discountDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDiscounts() {
        return discountService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDiscount(@RequestParam DiscountDto discountDto) {
        return discountService.saveDiscount(discountDto);
    }
}
