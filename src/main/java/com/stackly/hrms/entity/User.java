package com.stackly.hrms.entity;

import jakarta.persistence.*;
import lombok.*;

// User table for authentication

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username (login)
    @Column(unique = true)
    private String username;

    // Encrypted password
    private String password;

    /*
     * Role of user
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean active;

    /*
     * Link with Employee
     */
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}