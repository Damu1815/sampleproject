package com.hr.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department{

    @Id
    @NotNull
    @Column(name = "department_id")
    private Long departmentId;

    @NotNull
    @Column(name = "department_name")
    private String departmentName;
    
    @ManyToOne
    @JoinColumn(name ="manager_id",insertable = false, updatable = false)
    @JsonIgnore
    private Employee manager;
    @Column(name="manager_id")
    private Long mangerId;
    
    @ManyToOne
    @JoinColumn(name = "location_id",insertable = false, updatable = false)
    @JsonIgnore
    private Location location;
    @Column(name = "location_id")
    private Long locationId;
    
    }
