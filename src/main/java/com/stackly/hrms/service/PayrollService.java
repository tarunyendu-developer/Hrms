package com.stackly.hrms.service;

import com.stackly.hrms.entity.Payroll;
import java.time.YearMonth;

public interface PayrollService {
    Payroll generateSalary(Long employeeId, YearMonth month);
}