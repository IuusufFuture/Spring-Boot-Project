package com.example.finalporject.services.impl;

import com.example.finalporject.dao.OperRepo;
import com.example.finalporject.mappers.OperationDetailMapper;
import com.example.finalporject.mappers.OperationMapper;
import com.example.finalporject.models.dto.*;
import com.example.finalporject.models.entities.Operation;
import com.example.finalporject.models.entities.OperationDetail;
import com.example.finalporject.models.entities.Product;
import com.example.finalporject.models.entities.User;
import com.example.finalporject.models.responses.ErrorResponse;
import com.example.finalporject.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OperationServiceImpl implements OperationService {
    private OperRepo operRepo;
    private UserService userService;
    private ProductService productService;
    private PriceService priceService;
    private DiscountService discountService;

    public OperationServiceImpl(OperRepo operRepo, UserService userService, ProductService productService, PriceService priceService, DiscountService discountService) {
        this.operRepo = operRepo;
        this.userService = userService;
        this.productService = productService;
        this.priceService = priceService;
        this.discountService = discountService;

    }

    @Override
    public ResponseEntity<?> provide(String token, List<ProvideOperationDto> provideOperationDtoList) {
        ResponseEntity<?> responseEntity = userService.verifyUser(token);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        double price;
        double discount;
        double amount;
        double totalAmount = 0;
        List<OperationDetailDto> operationDetailDtos = new ArrayList<>();
        List<ProvideOperationDto> provOperList = new ArrayList<>();

        for (ProvideOperationDto each : provideOperationDtoList) {
            ProductDto productDto = productService.getByBarcode(each.getBarcode());

            if(Objects.isNull(productDto)) {
                return new ResponseEntity<>(new ErrorResponse("No product found in the database"), HttpStatus.NOT_FOUND);
            }

            OperationDetailDto operationDetail = new OperationDetailDto();

            operationDetail.setProductId(productDto);
            operationDetail.setQuantity(each.getCount());

            price = priceService.getPrice(productDto.getId());
            discount = discountService.getDiscount(productDto.getId());
            amount = price - (price * (discount/100));

            totalAmount += amount;
            operationDetail.setAmount(totalAmount);

            operationDetailDtos.add(operationDetail);

            ProvideOperationExtraDto theEndStep = new ProvideOperationExtraDto();
            // new comment
            // new comment 2
            theEndStep.setCount(each.getCount());
            theEndStep.setBarcode(each.getBarcode());
            theEndStep.setPrice(price);
            theEndStep.setDiscount(discount);
            theEndStep.setAmountAfterDiscount(amount);



            return null;//ResponseEntity.ok(OperationDetailMapper.INSTANCE.toOperationDetail(operationDetail));
        }


        /*

        * Создаем класс OperationDetailDto и обращаетесь в OperationDetailService save
        * return ProvideOperationDtoResponse
         * */
        return null;
    }

    @Override
    public ResponseEntity<?> pay(String token, Long operationId, double cash) {
        /*
        * 
        * */
        return null;
    }
}
