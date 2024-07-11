package com.springboot.springboot_restful_webservices.service;

import com.springboot.springboot_restful_webservices.dto.UserDto;
import com.springboot.springboot_restful_webservices.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);
}
