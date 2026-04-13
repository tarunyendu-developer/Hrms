package com.stackly.hrms.config;

import com.stackly.hrms.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// Security Configuration,Uses Basic Authentication,Role-based access control,Loads users from DB using CustomUserDetailsService
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)        //  Disable CSRF (for APIs)
                .authorizeHttpRequests(auth -> auth          //  Authorization rules
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()    //  Public endpoints (no login required)
                        .requestMatchers("/api/employees/**").hasAnyRole("ADMIN", "HR_MANAGER")      //  Employee APIs → ADMIN & HR
                        .requestMatchers("/api/departments/**").hasRole("ADMIN")         //  Department APIs → ADMIN only
                        .requestMatchers("/api/leaves/**").hasAnyRole("ADMIN", "HR_MANAGER", "EMPLOYEE")   //  Leave APIs → all roles
                        .requestMatchers("/api/payroll/**").hasRole("ADMIN")      //  Payroll APIs → ADMIN only
                        .anyRequest().authenticated()       // 🔒 Any other request must be authenticated
                )

                //  Load user from DB
                .userDetailsService(userDetailsService)

                //  Basic Authentication
                .httpBasic(withDefaults());

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}