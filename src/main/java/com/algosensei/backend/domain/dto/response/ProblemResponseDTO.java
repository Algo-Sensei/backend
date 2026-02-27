package com.algosensei.backend.domain.dto.response;

import com.algosensei.backend.domain.enums.Levels;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Levels difficultyLevel;
    private String tags;
    private Long userId;
}