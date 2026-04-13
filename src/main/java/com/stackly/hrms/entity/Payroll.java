package com.stackly.hrms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.YearMonth;

/*
 * Payroll Entity
 */
@Entity
@Table(name = "payroll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private YearMonth month;
    private int totalDays;
    private int presentDays;
    private double salary;
}