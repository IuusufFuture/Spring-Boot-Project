package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.PriceDto;
import com.example.finalporject.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceDto toPriceDto(Price price);
    Price toPrice(PriceDto priceDto);
}
