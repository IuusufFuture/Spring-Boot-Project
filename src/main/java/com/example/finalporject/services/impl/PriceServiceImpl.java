package com.example.finalporject.services.impl;

import com.example.finalporject.dao.PriceRepo;
import com.example.finalporject.mappers.PriceMapper;
import com.example.finalporject.mappers.ProductMapper;
import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {
    private PriceRepo priceRepo;

    public PriceServiceImpl(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Override
    public ResponseEntity<?> save(PriceDto priceDto) {
        List<Price> priceList = priceRepo.findAllByProduct(ProductMapper.INSTANCE.toProduct(priceDto.getProductId()));
        if(Objects.nonNull(priceList) && !priceList.isEmpty()) {
            priceList.stream().filter(
                    x -> x.getStartDate().before(priceDto.getStartDate()) || x.getStartDate().after(priceDto.getStartDate())
                    && x.getEndDate().before(priceDto.getEndDate()) || x.getEndDate().after(priceDto.getEndDate())
            ).forEach(x -> {
                x.setEndDate(new Date());
                priceRepo.save(x);
            });
        }

        Price price = PriceMapper.INSTANCE.toPrice(priceDto);
        price = priceRepo.save(price);
        return ResponseEntity.ok(PriceMapper.INSTANCE.toPriceDto(price));    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Price> priceList = priceRepo.findAll();
        return ResponseEntity.ok(priceList.stream().map(PriceMapper.INSTANCE::toPriceDto).collect(Collectors.toList()));
    }
}
