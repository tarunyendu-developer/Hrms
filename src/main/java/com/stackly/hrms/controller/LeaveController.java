package com.stackly.hrms.controller;

import com.stackly.hrms.dto.LeaveRequestDTO;
import com.stackly.hrms.entity.LeaveRequest;
import com.stackly.hrms.exception.ApiResponse;
import com.stackly.hrms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    //Apply leave
    @PostMapping("/apply")
    public ApiResponse<LeaveRequest> applyLeave(@RequestBody LeaveRequestDTO dto) {

        LeaveRequest leave = leaveService.applyLeave(dto);

        return ApiResponse.<LeaveRequest>builder()
                .success(true)
                .message("Leave applied successfully")
                .data(leave)
                .timestamp(LocalDateTime.now())
                .build();
    }

   //Get my leaves
    @GetMapping("/my")
    public ApiResponse<List<LeaveRequest>> getMyLeaves(@RequestParam Long employeeId) {

        List<LeaveRequest> list = leaveService.getMyLeaves(employeeId);

        return ApiResponse.<List<LeaveRequest>>builder()
                .success(true)
                .message("Leaves fetched successfully")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //Approve leave (HR/Admin)
    @PreAuthorize("hasAnyRole('ADMIN','HR_MANAGER')")
    @PutMapping("/{id}/approve")
    public ApiResponse<LeaveRequest> approveLeave(@PathVariable Long id, @RequestParam Long approverId) {

        LeaveRequest leave = leaveService.approveLeave(id, approverId);

        return ApiResponse.<LeaveRequest>builder()
                .success(true)
                .message("Leave approved")
                .data(leave)
                .timestamp(LocalDateTime.now())
                .build();
    }
   //Reject leave (HR/Admin)
   @PreAuthorize("hasAnyRole('ADMIN','HR_MANAGER')")
    @PutMapping("/{id}/reject")
    public ApiResponse<LeaveRequest> rejectLeave(@PathVariable Long id, @RequestParam Long approverId, @RequestParam String comment) {

        LeaveRequest leave = leaveService.rejectLeave(id, approverId, comment);

        return ApiResponse.<LeaveRequest>builder()
                .success(true)
                .message("Leave rejected")
                .data(leave)
                .timestamp(LocalDateTime.now())
                .build();
    }
}