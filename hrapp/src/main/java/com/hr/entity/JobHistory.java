package com.hr.entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "job_history")
@IdClass(JobHistoryId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory{

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id",insertable = false, updatable = false)
    @JsonManagedReference
    private Employee employee;
    @Column(name = "employee_id")
    private Long employeeId;

    @Id
    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "job_id",insertable = false, updatable = false)
    @JsonIgnore
    private Job job;
    @Column(name = "job_id")
    private String jobId;

    @ManyToOne
    @JoinColumn(name = "department_id",insertable = false, updatable = false)
    @JsonIgnore
    private Department department;
    @Column(name="department_id")
    private Long departmentId;

    
}
