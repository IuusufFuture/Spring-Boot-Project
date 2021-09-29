package com.example.finalporject.services.impl;

import com.example.finalporject.dao.PriceRepo;
import com.example.finalporject.mappers.PriceMapper;
import com.example.finalporject.mappers.ProductMapper;
import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.models.entities.Price;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.PriceService;
import com.example.finalporject.services.UserService;
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
    private UserService userService;

    public PriceServiceImpl(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Override
    public ResponseEntity<?> save(String token, PriceDto priceDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Price> priceList = priceRepo.findAllByProductId(ProductMapper.INSTANCE.toProduct(priceDto.getProductId()));
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
        return ResponseEntity.ok(PriceMapper.INSTANCE.toPriceDto(price));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Price> priceList = priceRepo.findAll();

        if(Objects.isNull(priceList)) {
            return new ResponseEntity<>(new ErrorResponse("Discount not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(priceList.stream().map(PriceMapper.INSTANCE::toPriceDto).collect(Collectors.toList()));
    }
}
