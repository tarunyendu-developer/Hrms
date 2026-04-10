package com.stackly.hrms.repository;

import com.stackly.hrms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Check duplicate email
    Optional<Employee> findByEmail(String email);

    // Search by name (useful later)
    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
}