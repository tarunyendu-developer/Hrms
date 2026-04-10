package com.stackly.hrms.exception;

import lombok.*;

import java.time.LocalDateTime;

// Generic API response wrapper

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;     // true or false
    private String message;      // message
    private T data;              // actual response
    private LocalDateTime timestamp;
}