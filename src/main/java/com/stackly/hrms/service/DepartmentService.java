package com.stackly.hrms.service;

import com.stackly.hrms.entity.Department;

import java.util.List;

//Department business logic
public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);
}