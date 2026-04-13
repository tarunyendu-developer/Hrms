package com.stackly.hrms.controller;

import com.hrms.dto.AuthResponse;
import com.stackly.hrms.dto.LoginRequest;
import com.stackly.hrms.entity.User;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Register API
    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody User user) {

        User saved = authService.register(user);

        return ApiResponse.<User>builder()
                .success(true)
                .message("User registered successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Login API
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }
}