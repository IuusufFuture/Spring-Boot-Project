package com.example.finalporject.controllers;

import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {

    private PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createPrice(@RequestHeader String token, @RequestBody PriceDto priceDto) {
        return priceService.savePrice(token, priceDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestHeader String token) {
        return priceService.getAll(token);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePrice(@RequestHeader String token, @RequestBody PriceDto priceDto) {
        return priceService.savePrice(token, priceDto);
    }

    @GetMapping("/getByPrice")
    public ResponseEntity<?> findPrice(@RequestHeader String token, @RequestParam Long id) {
        return priceService.findPrice(token, id);
    }
}
