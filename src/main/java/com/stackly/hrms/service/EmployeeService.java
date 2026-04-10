package com.stackly.hrms.service;

import com.stackly.hrms.entity.Employee;

import java.util.List;


public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);
}