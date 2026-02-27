package com.algosensei.backend.domain.entity;

import com.algosensei.backend.domain.enums.Levels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // problem title — optional
    @Column(nullable = true)
    private String title;

    // full problem description — required
    @NotBlank(message = "Description is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    // how hard the problem is: EASY=basic, MEDIUM=moderate, HARD=difficult, EXPERT=extremely hard
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Levels difficultyLevel;

    @Column(nullable = true)
    private String tags;

    /*
     * many problems belong to one user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}