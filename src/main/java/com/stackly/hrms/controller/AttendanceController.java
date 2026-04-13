package com.stackly.hrms.controller;

import com.stackly.hrms.dto.AttendanceDTO;
import com.stackly.hrms.entity.Attendance;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Mark attendance
    @PostMapping
    public ApiResponse<Attendance> markAttendance(@RequestBody AttendanceDTO dto) {

        Attendance attendance = attendanceService.markAttendance(dto);

        return ApiResponse.<Attendance>builder()
                .success(true)
                .message("Attendance marked successfully")
                .data(attendance)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Get attendance of employee
    @GetMapping
    public ApiResponse<List<Attendance>> getAttendance(@RequestParam Long employeeId) {

        List<Attendance> list = attendanceService.getAttendance(employeeId);

        return ApiResponse.<List<Attendance>>builder()
                .success(true)
                .message("Attendance fetched successfully")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
    }
}