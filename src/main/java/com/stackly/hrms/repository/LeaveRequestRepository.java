package com.stackly.hrms.repository;


import com.stackly.hrms.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Get all leaves of an employee
    List<LeaveRequest> findByEmployeeId(Long employeeId);
}