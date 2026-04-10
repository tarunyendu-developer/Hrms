package com.stackly.hrms.service;

import com.stackly.hrms.entity.Employee;
import com.stackly.hrms.entity.EmployeeStatus;
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
    public Employee createEmployee(Employee employee) {

        //  1. Check duplicate email
        employeeRepository.findByEmail(employee.getEmail()).ifPresent(e -> {throw new RuntimeException("Email already exists");});

        //  2. Generate Employee Code (EMP001)
        long count = employeeRepository.count() + 1;
        String empCode = String.format("EMP%03d", count);
        employee.setEmployeeCode(empCode);

        // Default values
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setCreatedAt(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    // Get all employees
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Get employee by ID
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}