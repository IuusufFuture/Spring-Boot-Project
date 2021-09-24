package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.UserEmailDto;
import com.example.finalporject.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEmailDto toUserEmailDto(User user);
}
