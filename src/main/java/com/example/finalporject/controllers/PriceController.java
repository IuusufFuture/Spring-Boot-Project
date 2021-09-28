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
    public ResponseEntity<?> createPrice(@RequestParam PriceDto priceDto) {
        return priceService.save(priceDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return priceService.getAll();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePrice(@RequestParam PriceDto priceDto) {
        return priceService.save(priceDto);
    }

//    @PutMapping("/delete")
//    public ResponseEntity<?> updatePrice(@RequestMapping ???) {
//        return priceService.save();
//    }
}
