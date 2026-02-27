package com.algosensei.backend.domain.dto.request;

import com.algosensei.backend.domain.enums.Levels;
import lombok.Data;

@Data
public class ProblemUpdateRequestDTO {

    private String title;
    private String description;
    private Levels difficultyLevel;  // update difficulty of the problem
    private String tags;
}