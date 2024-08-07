package com.springboot.springboot_restful_webservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto model information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;
}
