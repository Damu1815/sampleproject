package com.hr.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @Column(name = "region_id",columnDefinition = "decimal(4, 0)")
    private Long regionId;

    @NotNull
    @Column(name = "region_name")
    private String regionName;

    
}
