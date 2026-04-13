package com.stackly.hrms.service;

import com.stackly.hrms.dto.LeaveRequestDTO;
import com.stackly.hrms.entity.LeaveRequest;

import java.util.List;

public interface LeaveService {

    LeaveRequest applyLeave(LeaveRequestDTO dto);

    List<LeaveRequest> getMyLeaves(Long employeeId);

    LeaveRequest approveLeave(Long leaveId, Long approverId);

    LeaveRequest rejectLeave(Long leaveId, Long approverId, String comment);
}