package com.algosensei.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algosensei.backend.domain.dto.request.ProblemRequestDTO;
import com.algosensei.backend.domain.dto.response.ProblemResponseDTO;
import com.algosensei.backend.service.ProblemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    /*
     * POST /api/problems
     */
    @PostMapping
    public ResponseEntity<ProblemResponseDTO> create(@Valid @RequestBody ProblemRequestDTO request) {
        return ResponseEntity.ok(problemService.create(request));
    }

    /*
     * GET /api/problems/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(problemService.getById(id));
    }

    /*
     * GET /api/problems/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProblemResponseDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(problemService.getByUserId(userId));
    }
}