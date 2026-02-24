package com.algosensei.backend.domain.entity;

import java.util.List;

import com.algosensei.backend.domain.enums.Levels;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class User {

    // auto-generated primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // user's full name — cannot be blank
    @NotBlank(message = "Please enter your name")
    @Column(nullable = false)
    private String name;

    // user's email — must be unique and valid format
    @NotBlank(message = "Please enter your email")
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    // stored as bcrypt hash — never stored as plain text
    @NotBlank(message = "Please enter your password")
    @Column(nullable = false)
    private String password;

    // how skillful the user is: EASY=beginner, MEDIUM=intermediate, HARD=advanced, EXPERT=master
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Levels skillLevel;

    /*
     * one user can have many problems
     * - mappedBy = "user"       → Problem entity owns the relationship
     * - cascade = ALL           → save/update/delete cascades to problems
     * - orphanRemoval = true    → removed problems get deleted from DB
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems;
}