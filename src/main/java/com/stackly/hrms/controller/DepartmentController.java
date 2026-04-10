package com.stackly.hrms.controller;

import com.stackly.hrms.entity.Department;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Create Department
    @PostMapping
    public ApiResponse<Department> createDepartment(@RequestBody Department department) {

        Department saved = departmentService.createDepartment(department);

        return ApiResponse.<Department>builder()
                .success(true)
                .message("Department created successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Get all department
    @GetMapping
    public ApiResponse<List<Department>> getAllDepartments() {

        List<Department> list = departmentService.getAllDepartments();

        return ApiResponse.<List<Department>>builder()
                .success(true)
                .message("Departments fetched successfully")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Update department
    @PutMapping("/{id}")
    public ApiResponse<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {

        Department updated = departmentService.updateDepartment(id, department);

        return ApiResponse.<Department>builder()
                .success(true)
                .message("Department updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Delete department
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Department deleted successfully")
                .data("Deleted")
                .timestamp(LocalDateTime.now())
                .build();
    }
}