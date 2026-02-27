package com.algosensei.backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algosensei.backend.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}