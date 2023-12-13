package com.hr.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id" ,columnDefinition = "decimal(4, 0)")
    private Long employeeId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "salary")
    private Double salary;

    
    @Column(name = "commission_pct")
    private Double commissionPct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "department_id" )
    @JsonManagedReference
    private Department department;

    
}

