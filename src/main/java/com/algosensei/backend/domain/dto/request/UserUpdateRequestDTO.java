package com.algosensei.backend.domain.dto.request;

import com.algosensei.backend.domain.enums.Levels;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {

    private String username;

    @Email(message = "Email should be valid")
    private String email;

    private String password;

    private Levels skillLevel;
}