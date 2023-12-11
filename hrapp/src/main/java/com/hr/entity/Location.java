package com.hr.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @Column(name = "location_id",columnDefinition = "decimal(4, 0)")
    @JsonProperty(required=true)
    private Long locationId;

    @NotNull
    @Column(name = "street_address")
    @JsonProperty(required=true)
    private String streetAddress;

    @Column(name = "postal_code")
    @JsonProperty(required=true)
    private String postalCode;

    @NotNull
    @Column(name = "city")
    @JsonProperty(required=true)
    private String city;

    @Column(name = "state_province")
    @JsonProperty(required=true)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name="country_id")
    @JsonManagedReference
    private Country country;

    
}
