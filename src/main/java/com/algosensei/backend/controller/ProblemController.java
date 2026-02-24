package com.algosensei.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.algosensei.backend.domain.dto.request.ProblemRequestDTO;
import com.algosensei.backend.domain.dto.request.ProblemUpdateRequestDTO;
import com.algosensei.backend.domain.dto.response.ProblemResponseDTO;
import com.algosensei.backend.service.ProblemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // POST /api/problems
    @PostMapping
    public ResponseEntity<ProblemResponseDTO> create(@Valid @RequestBody ProblemRequestDTO request) {
        return ResponseEntity.ok(problemService.create(request));
    }

    // GET /api/problems
    @GetMapping
    public ResponseEntity<List<ProblemResponseDTO>> getAll() {
        return ResponseEntity.ok(problemService.getAll());
    }

    // GET /api/problems/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(problemService.getById(id));
    }

    // GET /api/problems/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProblemResponseDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(problemService.getByUserId(userId));
    }

    // PUT /api/problems/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProblemResponseDTO> update(@PathVariable Long id, @RequestBody ProblemUpdateRequestDTO request) {
        return ResponseEntity.ok(problemService.update(id, request));
    }

    // DELETE /api/problems/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        problemService.delete(id);
        return ResponseEntity.ok("Problem deleted successfully");
    }
}