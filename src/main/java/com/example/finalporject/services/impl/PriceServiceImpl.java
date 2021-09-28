package com.example.finalporject.services.impl;

import com.example.finalporject.dao.PriceRepo;
import com.example.finalporject.mappers.PriceMapper;
import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.models.entities.Price;
import com.example.finalporject.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {
    private PriceRepo priceRepo;

    public PriceServiceImpl(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Override
    public ResponseEntity<?> save(PriceDto priceDto) {
        Price price = PriceMapper.INSTANCE.toPrice(priceDto);
        price = priceRepo.save(price);
        return ResponseEntity.ok(PriceMapper.INSTANCE.toPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Price> priceList = priceRepo.findAll();
        return ResponseEntity.ok(priceList.stream().map(PriceMapper.INSTANCE::toPriceDto).collect(Collectors.toList()));
    }
}
