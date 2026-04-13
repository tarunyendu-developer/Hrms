package com.stackly.hrms.repository;

import com.stackly.hrms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Get attendance of employee by date
    Optional<Attendance> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    // Get all attendance of employee
    List<Attendance> findByEmployeeId(Long employeeId);
}