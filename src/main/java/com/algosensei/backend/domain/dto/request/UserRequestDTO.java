package com.algosensei.backend.domain.dto.request;

import com.algosensei.backend.domain.enums.Levels;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;


    // how skillful the user is
    @NotNull(message = "Skill level is required")
    private Levels skillLevel;
}