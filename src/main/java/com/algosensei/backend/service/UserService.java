package com.algosensei.backend.service;

import com.algosensei.backend.domain.dto.request.UserLoginRequestDTO;
import com.algosensei.backend.domain.dto.request.UserRequestDTO;
import com.algosensei.backend.domain.dto.response.UserResponseDTO;
import com.algosensei.backend.domain.entity.User;
import com.algosensei.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

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
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // save to database and return response (without password)
        return toResponseDTO(userRepository.save(user));
    }

    // login
    // verifies email and password returns user data if correct
    public UserResponseDTO login(UserLoginRequestDTO request) {

        // find user by email throw error if not found
        User user =  null;
        if(request.getEmail() != null && !request.getEmail().isEmpty()) {
            user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } else if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Username not found"));
        } else {
            throw new RuntimeException("Email or username not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

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
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}