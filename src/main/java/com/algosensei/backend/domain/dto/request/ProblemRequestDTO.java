package com.algosensei.backend.domain.dto.request;

import com.algosensei.backend.domain.enums.Levels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProblemRequestDTO {

    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private Levels difficultyLevel; // how skillful the user is

    private String tags;

    @NotNull(message = "User ID is required")
    private Long userId;


}