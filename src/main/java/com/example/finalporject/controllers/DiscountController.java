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
    public ResponseEntity<?> createDiscount(@RequestHeader String token, @RequestBody DiscountDto discountDto) {
        return discountService.saveDiscount(token, discountDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDiscounts(@RequestHeader String token) {
        return discountService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDiscount(@RequestHeader String token, @RequestBody DiscountDto discountDto) {
        return discountService.saveDiscount(token, discountDto);
    }

    @GetMapping("/getByDiscount")
    public ResponseEntity<?> findDiscount(@RequestHeader String token, @RequestParam Long id) {
        return discountService.findDiscount(token, id);
    }

}
