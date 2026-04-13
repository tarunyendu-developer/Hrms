package com.stackly.hrms.controller;

import com.stackly.hrms.dto.EmployeeRequestDTO;
import com.stackly.hrms.dto.EmployeeResponseDTO;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Create Employee
    @PreAuthorize("hasAnyRole('ADMIN','HR_MANAGER')")
    @PostMapping
    public ApiResponse<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {

        EmployeeResponseDTO response = employeeService.createEmployee(dto);

        return ApiResponse.<EmployeeResponseDTO>builder()
                .success(true)
                .message("Employee created successfully")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }

   //Get all employees
   @PreAuthorize("hasAnyRole('ADMIN','HR_MANAGER')")
    @GetMapping
    public ApiResponse<List<EmployeeResponseDTO>> getAllEmployees() {

        List<EmployeeResponseDTO> list = employeeService.getAllEmployees();

        return ApiResponse.<List<EmployeeResponseDTO>>builder()
                .success(true)
                .message("Employees fetched successfully")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Get employee by ID
    @PreAuthorize("hasAnyRole('ADMIN','HR_MANAGER','EMPLOYEE')")
    @GetMapping("/{id}")
    public ApiResponse<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {

        EmployeeResponseDTO emp = employeeService.getEmployeeById(id);

        return ApiResponse.<EmployeeResponseDTO>builder()
                .success(true)
                .message("Employee fetched successfully")
                .data(emp)
                .timestamp(LocalDateTime.now())
                .build();
    }
}