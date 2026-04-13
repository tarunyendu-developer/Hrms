package com.stackly.hrms.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Many attendance records → One employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //Attendance date
    @Column(nullable = false)
    private LocalDate date;

    //Present or Absent
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;
}