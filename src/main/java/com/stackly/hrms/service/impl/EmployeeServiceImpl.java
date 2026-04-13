package com.stackly.hrms.service.impl;

import com.stackly.hrms.dto.EmployeeRequestDTO;
import com.stackly.hrms.dto.EmployeeResponseDTO;
import com.stackly.hrms.entity.Employee;
import com.stackly.hrms.entity.EmployeeStatus;
import com.stackly.hrms.exception.BusinessException;
import com.stackly.hrms.exception.ResourceNotFoundException;
import com.stackly.hrms.repository.EmployeeRepository;
import com.stackly.hrms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Create Employee
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        // Check duplicate email
        employeeRepository.findByEmail(dto.getEmail())
                .ifPresent(e -> {
                    throw new BusinessException("Email already exists");
                });

        // Convert DTO → Entity
        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dateOfBirth(dto.getDateOfBirth())
                .joiningDate(dto.getJoiningDate())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .status(EmployeeStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        // Generate employee code
        long count = employeeRepository.count() + 1;
        employee.setEmployeeCode(String.format("EMP%03d", count));

        Employee saved = employeeRepository.save(employee);

        // Convert Entity → DTO
        return EmployeeResponseDTO.builder()
                .id(saved.getId())
                .employeeCode(saved.getEmployeeCode())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .designation(saved.getDesignation())
                .salary(saved.getSalary())
                .status(saved.getStatus().name())
                .joiningDate(saved.getJoiningDate())
                .build();
    }
    // Get all employees
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> EmployeeResponseDTO.builder()
                        .id(emp.getId())
                        .employeeCode(emp.getEmployeeCode())
                        .firstName(emp.getFirstName())
                        .lastName(emp.getLastName())
                        .email(emp.getEmail())
                        .designation(emp.getDesignation())
                        .salary(emp.getSalary())
                        .status(emp.getStatus().name())
                        .joiningDate(emp.getJoiningDate())
                        .build()
                )
                .toList();
    }

    //Get Employee by ID
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return EmployeeResponseDTO.builder()
                .id(emp.getId())
                .employeeCode(emp.getEmployeeCode())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .email(emp.getEmail())
                .designation(emp.getDesignation())
                .salary(emp.getSalary())
                .status(emp.getStatus().name())
                .joiningDate(emp.getJoiningDate())
                .build();
    }
}