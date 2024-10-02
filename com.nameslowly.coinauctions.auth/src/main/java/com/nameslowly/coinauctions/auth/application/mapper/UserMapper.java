package com.nameslowly.coinauctions.auth.application.mapper;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.nameslowly.coinauctions.auth.application.dto.UserDto;
import com.nameslowly.coinauctions.auth.application.dto.UserInfoResponseDto;
import com.nameslowly.coinauctions.auth.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    // entity to Dto
    UserInfoResponseDto userToUserInfoResponseDto(User user);

    // entity to Dto
    UserDto userToUserDto(User user);

    // Dto to entity
    User userDtoToUser(UserDto userDto);
}