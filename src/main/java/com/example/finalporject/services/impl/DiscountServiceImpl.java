package com.example.finalporject.services.impl;

import com.example.finalporject.dao.DiscountRepo;
import com.example.finalporject.mappers.DiscountMapper;
import com.example.finalporject.mappers.ProductMapper;
import com.example.finalporject.models.dto.DiscountDto;
import com.example.finalporject.models.entities.Discount;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.DiscountService;
import com.example.finalporject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    private DiscountRepo discountRepo;
    private UserService userService;

    public DiscountServiceImpl(DiscountRepo discountRepo, UserService userService) {
        this.discountRepo = discountRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> saveDiscount(String token, DiscountDto discountDto) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Discount> discountList = discountRepo.findAllByProductId(ProductMapper.INSTANCE.toProduct(discountDto.getProductId()));
        if(Objects.nonNull(discountList) && !discountList.isEmpty()) {
            discountList.stream().filter(
                    x -> x.getStartDate().before(discountDto.getStartDate()) || x.getStartDate().after(discountDto.getStartDate())
                            && x.getEndDate().before(discountDto.getEndDate()) || x.getEndDate().after(discountDto.getEndDate())
            ).forEach(x -> {
                x.setEndDate(new Date());
                discountRepo.save(x);
            });
        }
        Discount discount = DiscountMapper.INSTANCE.toDiscount(discountDto);
        discount = discountRepo.save(discount);
        return ResponseEntity.ok(DiscountMapper.INSTANCE.toDiscountDto(discount));
    }

    @Override
    public ResponseEntity<?> getAll(String token) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        List<Discount> discountList = discountRepo.findAll();

        if(Objects.isNull(discountList)) {
            return new ResponseEntity<>(new ErrorResponse("Discount not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(discountList.stream().map(DiscountMapper.INSTANCE::toDiscountDto).collect(Collectors.toList()));
    }
}
