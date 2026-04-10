package com.stackly.hrms.service;

import com.stackly.hrms.dto.EmployeeRequestDTO;
import com.stackly.hrms.dto.EmployeeResponseDTO;

import java.util.List;


public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);
}