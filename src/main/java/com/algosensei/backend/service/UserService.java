package com.algosensei.backend.service;
import com.algosensei.backend.domain.dto.request.UserLoginRequestDTO;
import com.algosensei.backend.domain.dto.request.UserRequestDTO;
import com.algosensei.backend.domain.dto.response.UserResponseDTO;
import com.algosensei.backend.domain.dto.response.UserUpdateRequestDT0;
import com.algosensei.backend.domain.entity.User;
import com.algosensei.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserResponseDTO userResponseDTO;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    // register new account
    public UserResponseDTO Register(UserRequestDTO userRequestDTO, PasswordEncoder passwordEncoder) {
        if (userResponseDTO.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User(builder)
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .levels(userRequestDTO.getLevels())
                .build();

        return toResponseDTO(userRepository.save(user));
    }


    //login
    public UserRequestDTO login(UserLoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return toResponseDTO(user);
    }

    //todo
}
