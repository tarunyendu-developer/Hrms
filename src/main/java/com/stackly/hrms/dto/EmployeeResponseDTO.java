package com.stackly.hrms.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

//Used to send data back to client
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO {

    private Long id;
    private String employeeCode;

    private String firstName;
    private String lastName;
    private String email;

    private String designation;
    private BigDecimal basicSalary;

    private String status;

    private LocalDate joiningDate;
}