package com.hr.entity;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "job_history")
@IdClass(JobHistoryId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    @Id
    @Column(name = "start_date")
    @JsonProperty(required=true)
    private Date startDate;

   
    @Column(name = "end_date")
    @JsonProperty(required=true)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonManagedReference
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    private Department department;

    
}