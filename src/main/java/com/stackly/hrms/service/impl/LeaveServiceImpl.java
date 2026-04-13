package com.stackly.hrms.service.impl;

import com.stackly.hrms.dto.LeaveRequestDTO;
import com.stackly.hrms.entity.Employee;
import com.stackly.hrms.entity.LeaveRequest;
import com.stackly.hrms.entity.LeaveStatus;
import com.stackly.hrms.exception.ResourceNotFoundException;
import com.stackly.hrms.repository.EmployeeRepository;
import com.stackly.hrms.repository.LeaveRequestRepository;
import com.stackly.hrms.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    //Apply Leave
    @Override
    public LeaveRequest applyLeave(LeaveRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Calculate total days
        int days = (int) ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

        LeaveRequest leave = LeaveRequest.builder()
                .employee(employee)
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .totalDays(days)
                .reason(dto.getReason())
                .status(LeaveStatus.PENDING)
                .appliedAt(LocalDateTime.now())
                .build();

        return leaveRepository.save(leave);
    }

    //Get my leaves
    @Override
    public List<LeaveRequest> getMyLeaves(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

   //Approve Leave
    @Override
    public LeaveRequest approveLeave(Long leaveId, Long approverId) {

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        Employee approver = employeeRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));

        // Only PENDING can be approved
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Leave already processed");
        }

        leave.setStatus(LeaveStatus.APPROVED);
        leave.setApprovedBy(approver);

        return leaveRepository.save(leave);
    }
   //Reject Leave
    @Override
    public LeaveRequest rejectLeave(Long leaveId, Long approverId, String comment) {

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        Employee approver = employeeRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));

        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException("Leave already processed");
        }

        leave.setStatus(LeaveStatus.REJECTED);
        leave.setApprovedBy(approver);
        leave.setManagerComments(comment);

        return leaveRepository.save(leave);
    }
}