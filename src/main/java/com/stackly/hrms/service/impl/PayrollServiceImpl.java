package com.stackly.hrms.service.impl;

import com.stackly.hrms.entity.*;
import com.stackly.hrms.exception.ResourceNotFoundException;
import com.stackly.hrms.repository.AttendanceRepository;
import com.stackly.hrms.repository.EmployeeRepository;
import com.stackly.hrms.repository.PayrollRepository;
import com.stackly.hrms.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final PayrollRepository payrollRepository;

    //Generate salary based on attendance
    @Override
    public Payroll generateSalary(Long employeeId, YearMonth month) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        List<Attendance> attendanceList = attendanceRepository.findByEmployeeId(employeeId);

        int totalDays = month.lengthOfMonth();

        // Count present days
        int presentDays = (int) attendanceList.stream()
                .filter(a -> a.getDate().getMonth().equals(month.getMonth())
                        && a.getDate().getYear() == month.getYear()
                        && a.getStatus() == AttendanceStatus.PRESENT)
                .count();

        // Simple salary logic
        double perDaySalary = employee.getSalary().doubleValue() / totalDays;
        double finalSalary = perDaySalary * presentDays;

        Payroll payroll = Payroll.builder()
                .employee(employee)
                .month(month)
                .totalDays(totalDays)
                .presentDays(presentDays)
                .salary(finalSalary)
                .build();

        return payrollRepository.save(payroll);
    }
}