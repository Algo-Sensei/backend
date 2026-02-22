package com.algosensei.backend.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserLoginRequestDTO {
    
    @NotBlank(message="Email is requred")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Password is required")
    private String password;
}   
