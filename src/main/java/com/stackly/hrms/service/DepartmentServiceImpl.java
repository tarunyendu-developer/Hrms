package com.stackly.hrms.service;

import com.stackly.hrms.entity.Department;
import com.stackly.hrms.exception.BusinessException;
import com.stackly.hrms.exception.ResourceNotFoundException;
import com.stackly.hrms.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    //Create Department
    @Override
    public Department createDepartment(Department department) {

        // Check duplicate name
        departmentRepository.findByName(department.getName())
                .ifPresent(d -> {
                    throw new BusinessException("Department already exists");
                });

        return departmentRepository.save(department);
    }

    // Get all departments
    @Override
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    // Update department
    @Override
    public Department updateDepartment(Long id, Department department) {

        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        existing.setName(department.getName());
        existing.setDescription(department.getDescription());

        return departmentRepository.save(existing);
    }

    //Delete department
    @Override
    public void deleteDepartment(Long id) {

        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        departmentRepository.delete(dept);
    }
}