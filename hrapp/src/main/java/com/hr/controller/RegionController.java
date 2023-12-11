package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Region;
import com.hr.repository.RegionRepository;

@RestController
public class RegionController {
	   @Autowired
	    private RegionRepository regionRepository;
	    
	   @GetMapping("/allregions")
	   public List<Region> getAll(){
		   return  regionRepository.findAll();
	   }
	   
}
