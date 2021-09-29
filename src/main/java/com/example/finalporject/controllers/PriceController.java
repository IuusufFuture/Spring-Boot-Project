package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createPrice(@RequestHeader String token, @RequestParam PriceDto priceDto) {
        return priceService.save(token, priceDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        return priceService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePrice(@RequestHeader String token, @RequestParam PriceDto priceDto) {
        return priceService.save(token, priceDto);
    }
}
