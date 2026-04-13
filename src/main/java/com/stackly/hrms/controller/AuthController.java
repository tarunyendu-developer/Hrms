package com.stackly.hrms.controller;

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

    //  Register API
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
}