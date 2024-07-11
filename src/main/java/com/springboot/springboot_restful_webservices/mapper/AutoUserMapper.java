package com.springboot.springboot_restful_webservices.mapper;

import com.springboot.springboot_restful_webservices.dto.UserDto;
import com.springboot.springboot_restful_webservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper AUTO_USER_MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

}
