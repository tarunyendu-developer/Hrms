package com.hrms.dto;

import lombok.*;

// Response after login
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
}