package com.algosensei.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algosensei.backend.domain.dto.request.UserLoginRequestDTO;
import com.algosensei.backend.domain.dto.request.UserRequestDTO;
import com.algosensei.backend.domain.dto.response.UserResponseDTO;
import com.algosensei.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     * REGISTER
     * POST /api/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.register(request));
    }

    /*
     * LOGIN
     * POST /api/users/login
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO request) {
        return ResponseEntity.ok(userService.login(request));
    }

    /*
     * GET USER PROFILE
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }
}