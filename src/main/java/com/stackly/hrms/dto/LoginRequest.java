package com.stackly.hrms.dto;

import lombok.*;

//Used for login request
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}