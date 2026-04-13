package com.stackly.hrms.service;
;
import com.stackly.hrms.dto.LoginRequest;
import com.stackly.hrms.entity.User;

public interface AuthService {

    com.hrms.dto.AuthResponse login(LoginRequest request);

    User register(User user);
}