package com.algosensei.backend.service;

import com.algosensei.backend.domain.dto.request.UserLoginRequestDTO;
import com.algosensei.backend.domain.dto.request.UserRequestDTO;
import com.algosensei.backend.domain.dto.response.UserResponseDTO;
import com.algosensei.backend.domain.entity.User;
import com.algosensei.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/*
 * UserService handles all business logic related to User
 * - register    → create new account
 * - login       → verify credentials
 * - getById     → get user profile
 *
 * NOTE: no update or delete
 * - this system is like ChatGPT — users keep their account and history
 * - account management (update/delete) will be added later if needed
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // registration
    // creates a new user account
    public UserResponseDTO register(UserRequestDTO request) {

        // check if email is already taken
        // we don't want two accounts with the same email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // build user  hash password before saving
        // never store plain text passwords in the database
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .skillLevel(request.getSkillLevel()) // EASY, MEDIUM, HARD, EXPERT
                .build();

        // save to database and return response (without password)
        return toResponseDTO(userRepository.save(user));
    }

    // login
    // verifies email and password returns user data if correct
    public UserResponseDTO login(UserLoginRequestDTO request) {

        // find user by email throw error if not found
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // heck if the password matches the hashed password in the database
        // passwordEncoder.matches() compares plain text vs hashed
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // credentials are correct return user data
        return toResponseDTO(user);
    }

    // get by ID
    // returns the user's profile data
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toResponseDTO(user);
    }

    // helper
    // converts user entity UserResponseDTO
    // password is intentionally excluded never send it back to the client
    private UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .skillLevel(user.getSkillLevel())
                .build();
    }
}