package com.springboot.springboot_restful_webservices.serviceImpl;

import com.springboot.springboot_restful_webservices.dto.UserDto;
import com.springboot.springboot_restful_webservices.entity.User;
import com.springboot.springboot_restful_webservices.exception.EmailAlreadyExistException;
import com.springboot.springboot_restful_webservices.exception.ResourceNotFoundException;
import com.springboot.springboot_restful_webservices.mapper.AutoUserMapper;
import com.springboot.springboot_restful_webservices.mapper.UserMapper;
import com.springboot.springboot_restful_webservices.repository.UserRepository;
import com.springboot.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already exist for a user");
        }

        // UserMapper to convert UserDTO to JPA Entity
        //User user = UserMapper.mapToUser(userDto);

        // ModelMapper to convert UserDTO to JPA Entity
        //User user = modelMapper.map(userDto, User.class);

        // MapStruct to convert UserDTO to JPA Entity
        User user = AutoUserMapper.AUTO_USER_MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        // UserMapper to convert JPA to UserDTO Entity
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        // ModelMapper to convert JPA to UserDTO Entity
        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        // MapStruct to convert JPA to UserDTO Entity
        UserDto savedUserDto = AutoUserMapper.AUTO_USER_MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
       User user = userRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("User", "id", id)
       );

        // UserMapper to convert JPA to UserDTO Entity
//        return UserMapper.mapToUserDto(user);

        // ModelMapper to convert JPA to UserDTO Entity
        //return modelMapper.map(user, UserDto.class);

        // MapStruct to convert JPA to UserDTO Entity
        return AutoUserMapper.AUTO_USER_MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        // UserMapper to convert JPA to UserDTO Entity
//        return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        // ModelMapper to convert JPA to UserDTO Entity
//        return allUsers.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        // MapStruct to convert JPA to UserDTO Entity
        return allUsers.stream().map(user -> AutoUserMapper.AUTO_USER_MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }


    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // UserMapper to convert JPA to UserDTO Entity
//        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);

        // ModelMapper to convert JPA to UserDTO Entity
        //UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);

        // MapStruct to convert JPA to UserDTO Entity
        UserDto updatedUserDto = AutoUserMapper.AUTO_USER_MAPPER.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.deleteById(id);
    }
}
