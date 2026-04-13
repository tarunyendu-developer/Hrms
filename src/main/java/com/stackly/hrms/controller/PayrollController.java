package com.stackly.hrms.controller;

import com.stackly.hrms.entity.Payroll;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollService payrollService;

 //Generate salary
    @PostMapping("/generate")
    public ApiResponse<Payroll> generateSalary(@RequestParam Long employeeId, @RequestParam String month) {

        Payroll payroll = payrollService.generateSalary(employeeId, YearMonth.parse(month));

        return ApiResponse.<Payroll>builder()
                .success(true)
                .message("Salary generated successfully")
                .data(payroll)
                .timestamp(LocalDateTime.now())
                .build();
    }
}