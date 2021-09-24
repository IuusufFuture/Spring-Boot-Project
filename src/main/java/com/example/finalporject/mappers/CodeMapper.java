package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.CodeDto;
import com.example.finalporject.models.entities.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {
    CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);

    Code toCode(CodeDto codeDto);
    CodeDto toCodeDto(Code code);
}
