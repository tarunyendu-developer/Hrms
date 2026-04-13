package com.stackly.hrms.dto;

import com.stackly.hrms.entity.LeaveType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDTO {

    private Long employeeId;
    private LeaveType leaveType;

    private LocalDate startDate;
    private LocalDate endDate;

    private String reason;
}