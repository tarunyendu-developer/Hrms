package com.stackly.hrms.service;

import com.stackly.hrms.dto.AttendanceDTO;
import com.stackly.hrms.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance markAttendance(AttendanceDTO dto);
    List<Attendance> getAttendance(Long employeeId);
}