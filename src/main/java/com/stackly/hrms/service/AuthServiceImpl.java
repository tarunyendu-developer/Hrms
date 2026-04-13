package com.stackly.hrms.service;

import com.stackly.hrms.entity.Role;
import com.stackly.hrms.entity.User;
import com.stackly.hrms.exception.BusinessException;
import com.stackly.hrms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//AuthServiceImpl ,Handles user registration ,Encrypts password Assigns default role
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Register new user
    @Override
    public User register(User user) {

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BusinessException("Username is required");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BusinessException("Password is required");
        }

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new BusinessException("Username already exists");
                });

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole(Role.ROLE_EMPLOYEE);

        user.setActive(true);

        return userRepository.save(user);
    }
}