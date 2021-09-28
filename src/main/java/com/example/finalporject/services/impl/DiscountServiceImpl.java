package com.example.finalporject.services.impl;

import com.example.finalporject.dao.DiscountRepo;
import com.example.finalporject.mappers.DiscountMapper;
import com.example.finalporject.models.dto.DiscountDto;
import com.example.finalporject.models.entities.Discount;
import com.example.finalporject.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    private DiscountRepo discountRepo;

    public DiscountServiceImpl(DiscountRepo discountRepo) {
        this.discountRepo = discountRepo;
    }

    @Override
    public ResponseEntity<?> saveDiscount(DiscountDto discountDto) {
        Discount discount = DiscountMapper.INSTANCE.toDiscount(discountDto);
        discount = discountRepo.save(discount);
        return ResponseEntity.ok(DiscountMapper.INSTANCE.toDiscountDto(discount));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Discount> discountList = discountRepo.findAll();
        return ResponseEntity.ok(discountList.stream().map(DiscountMapper.INSTANCE::toDiscountDto).collect(Collectors.toList()));
    }
}
