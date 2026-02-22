package com.algosensei.backend.domain.dto.request;
import com.algosensei.backend.domain.enums.Levels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

import lombok.Data;


@Data
public class UserRequestDTO {
    
    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Email should be valid")
    private String email;

    @NotBlank(message="Password is required")
    private String password;

    
    @NotNull(message="sadfd")
    private Levels levels;

}