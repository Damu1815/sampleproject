package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Location;
import com.hr.repository.LocationRepository;
@RestController
public class LocationController {
	   @Autowired
	    private LocationRepository locationRepository;
	    
	   @GetMapping("/alllocations")
	   public List<Location> getAll(){
		   return locationRepository.findAll();
	   }
}
