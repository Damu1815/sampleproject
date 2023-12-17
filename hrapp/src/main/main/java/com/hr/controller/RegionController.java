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
 
import com.hr.entity.Region;
import com.hr.repository.RegionRepository;
import com.hr.service.RegionService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
 
	@Autowired
    RegionRepository regionRepository;
	@Autowired
	RegionService regionService;

	@PostMapping
	ResponseEntity<String> addRegion(@RequestBody Region region){
		 regionService.saveRegion(region);
		 return new ResponseEntity<>("Record Created Successfully",HttpStatus.CREATED);
	}
	@PutMapping()
	ResponseEntity<String> modifyRegion(@Valid @RequestBody Region region){
		 regionService.updateRegion(region);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);	
		}
	@GetMapping()
	 ResponseEntity<List<Region>> getAllRegions(){
		List<Region> regionList = regionRepository.findAll();
		return new ResponseEntity<>(regionList,HttpStatus.OK);
	}
     
	@GetMapping("/{region_id}")
	public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
		Region a=regionService.getRegionById(id);
		return new ResponseEntity<Region>(a, HttpStatus.OK);
	}
	
	 @DeleteMapping("/{region_id}")
	    public ResponseEntity<String> deleteRegionById(@PathVariable("id") Long id) {
	        regionService.deleteRegionById(id);
	        return ResponseEntity.ok("Region deleted successfully");
	    }
}