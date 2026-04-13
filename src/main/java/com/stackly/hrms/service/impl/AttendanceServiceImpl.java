package com.stackly.hrms.service.impl;

import com.stackly.hrms.dto.AttendanceDTO;
import com.stackly.hrms.entity.Attendance;
import com.stackly.hrms.entity.Employee;
import com.stackly.hrms.exception.BusinessException;
import com.stackly.hrms.exception.ResourceNotFoundException;
import com.stackly.hrms.repository.AttendanceRepository;
import com.stackly.hrms.repository.EmployeeRepository;
import com.stackly.hrms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    //Mark attendance
    @Override
    public Attendance markAttendance(AttendanceDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Prevent duplicate attendance for same date
        attendanceRepository.findByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate())
                .ifPresent(a -> {
                    throw new BusinessException("Attendance already marked for this date");
                });

        Attendance attendance = Attendance.builder()
                .employee(employee)
                .date(dto.getDate())
                .status(dto.getStatus())
                .build();

        return attendanceRepository.save(attendance);
    }

    //Get attendance of employee
    @Override
    public List<Attendance> getAttendance(Long employeeId) {

        return attendanceRepository.findByEmployeeId(employeeId);
    }
}