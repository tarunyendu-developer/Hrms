package com.stackly.hrms.repository;

import com.stackly.hrms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository for User table
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}