package com.algosensei.backend.domain.dto.response;

import com.algosensei.backend.domain.enums.Levels;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
}