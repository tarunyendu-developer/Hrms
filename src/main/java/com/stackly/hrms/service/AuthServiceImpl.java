package com.stackly.hrms.service;

import com.stackly.hrms.config.JwtUtil;
import com.stackly.hrms.dto.LoginRequest;
import com.stackly.hrms.entity.Role;
import com.stackly.hrms.entity.User;
import com.stackly.hrms.exception.BusinessException;
import com.stackly.hrms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //Register user
    @Override
    public User register(User user) {

        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new BusinessException("Username already exists");
                });

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role
        user.setRole(Role.ROLE_EMPLOYEE);
        user.setActive(true);

        return userRepository.save(user);
    }

    // Login user
    @Override
    public com.hrms.dto.AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException("Invalid username or password"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid username or password");
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getUsername());

        return com.hrms.dto.AuthResponse.builder()
                .token(token)
                .build();
    }
}