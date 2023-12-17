package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Location;
import com.hr.service.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
	
	@Autowired
	LocationService LocationService;
	
	@GetMapping("/findAllLocations")
	public ResponseEntity<List<Location>> findAllLocations(){
		List<Location> location = LocationService.findAllLocations();
		return new ResponseEntity<>(location,HttpStatus.OK);
	}
	
	@GetMapping("/locationId/{locationId}")
	public ResponseEntity<Location> findLocationById(@Valid @PathVariable Long locationId){
		
		Location location = LocationService.findById(locationId);
		return new ResponseEntity<>(location,HttpStatus.OK);
		
	}
	     
	 @PostMapping
	 public ResponseEntity<String> addNewLocation(@RequestBody Location locationId){
		 LocationService.addNewLocation(locationId);
		return new ResponseEntity<>("Location Created Successfully",HttpStatus.CREATED);
		 
	 }
	 
	 @PutMapping(value = "/{locationId}/loc/{newLocationId}")
	 public ResponseEntity<String> updateLocation(@PathVariable Long locationId, @PathVariable Long newLocationId){
		 LocationService.updateLocation(locationId, newLocationId);
		return new ResponseEntity<>("Location updated Succesfully",HttpStatus.OK);
		
	 }
	 
	 @DeleteMapping(value = "/{locationId}")
		public ResponseEntity<String> deleteLocationById(@PathVariable Long locationId){
			LocationService.deleteLocationById(locationId);
			return new ResponseEntity<>("Location details deleted successfully", HttpStatus.OK);
		}
	     

}

	 
	 
	 



