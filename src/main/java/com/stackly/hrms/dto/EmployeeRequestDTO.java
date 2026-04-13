package com.stackly.hrms.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

// Used when creating/updating employee
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone No is required")
    private String phone;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Joining Date is required")
    private LocalDate joiningDate;

    @NotNull(message = "Designation is required")
    private String designation;

    @NotNull(message = "Salary is required")
    private BigDecimal salary;

    private Long departmentId;
}