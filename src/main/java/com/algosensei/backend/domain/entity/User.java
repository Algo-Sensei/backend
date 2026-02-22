package com.algosensei.backend.domain.entity;
import java.util.List;

import com.algosensei.backend.domain.enums.Levels;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "users")
public class User {

    //will generate id each user
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    // user's name cannot be blank
    @NotBlank(message = "Please enter your name")
    @Column(nullable=false)
    private String name;

    // user's email must be unique and valid
    @NotBlank(message = "Please enter your email")
    @Column(nullable=false, unique=true)
    @Email(message = "Email should be valid")
    private String email;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Levels skillLevel;


    /*

    this means one user many problems

    - mappedBy = "user"
    ang problem entity ang nag handle sa relationship gikan sa 'user' field
    

    - cascade = CascadeType.ALL
    ang mahitabo ani sa user kay (save, update, delete) ma apil sad iya problem.

    - orphanRemoval = true
    kung tangalon ang isa ka problem sa list sa user, automatic ma delete sad sa iya database

    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problems;

}
