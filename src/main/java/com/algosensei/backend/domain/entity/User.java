package com.algosensei.backend.domain.entity;

import java.util.List;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* user's full name */
    @NotBlank(message = "Please enter your name")
    @Column(nullable = false)
    private String username;

    /* user's email — must be unique */
    @NotBlank(message = "Please enter your email")
    @Column(nullable = false, unique = true)
    @Email(message = "Email should be valid")
    private String email;

    /* stored as bcrypt hash — never stored as plain text */
    @NotBlank(message = "Please enter your password")
    @Column(nullable = false)
    private String password;

    /*
     * one user can have many problems
     * when user is deleted, all their problems are deleted too
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems;
}