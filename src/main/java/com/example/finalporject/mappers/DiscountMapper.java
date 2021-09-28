package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.DiscountDto;
import com.example.finalporject.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    DiscountDto toDiscountDto(Discount discount);
    Discount toDiscount(DiscountDto discountDto);
}
