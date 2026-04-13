package com.stackly.hrms.dto;

import com.stackly.hrms.entity.AttendanceStatus;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTO {

    private Long employeeId;
    private LocalDate date;
    private AttendanceStatus status;
}