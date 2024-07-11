package com.springboot.springboot_restful_webservices.controller;

import com.springboot.springboot_restful_webservices.dto.UserDto;
import com.springboot.springboot_restful_webservices.exception.ErrorDetails;
import com.springboot.springboot_restful_webservices.exception.ResourceNotFoundException;
import com.springboot.springboot_restful_webservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for user response",
        description = "CRUD REST APIs - CREATE, UPDATE, GET User, GET All Users, DELETE User"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "Create user REST API",
            description = "Create user REST API is use to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // Build Create user REST API with Post
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Build get user REST API with GET
    @Operation(
            summary = "Get user REST API",
            description = "Get user REST API is use to get user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public  ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get user REST API",
            description = "Get user REST API is use to get all users from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build get all Rest api with GET
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @Operation(
            summary = "UPDATE user REST API",
            description = "Update user REST API is use to update user in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build update REST API with update
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto userDto){
        userDto.setId(userId);
        UserDto updateUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @Operation(
            summary = "DELETE user REST API",
            description = "DELETE user REST API is use to delete user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build Delete REST API with Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
