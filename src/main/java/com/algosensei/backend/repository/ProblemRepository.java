package com.algosensei.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algosensei.backend.domain.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    
    List<Problem> findByUserId (Long userId);
}
