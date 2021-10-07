package com.example.finalporject.mappers;

import com.example.finalporject.models.dto.UserDto;
import com.example.finalporject.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
