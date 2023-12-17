package com.hr.controller;

import java.util.Collection;
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
 
import com.hr.entity.Job;
import com.hr.repository.JobRepository;
import com.hr.service.JobService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping(path="/api/v1/job" ,produces = "application/json")
public class JobController {
	@Autowired
	JobRepository jobRepository;
	@Autowired
	JobService jobService;
	@GetMapping()
	public Collection<Job> getAll() {
		return jobRepository.findAll();
	}
	@PostMapping
	ResponseEntity<String> addJob(@RequestBody Job job){
	 jobService.saveJob(job);
	 return new ResponseEntity<>("Record Created Successfully",HttpStatus.CREATED);
	}
	@PutMapping()
	ResponseEntity<String> modifyJob(@Valid @RequestBody Job job){
		 jobService.modifyJob(job);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);	
		}
	@PutMapping("/{minsalary}/{maxsalary}")
	ResponseEntity<String> modifySalary(@Valid @RequestBody Job job,@PathVariable Long minSalary,@PathVariable Long maxSalary){
		 jobService.updateSalary(job,minSalary,maxSalary);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);	
		}
	@DeleteMapping("/{job_id}")
	ResponseEntity<String> deleteByJobId(@Valid @RequestBody String job) {
		jobService.deleteJobById(job);
		return new ResponseEntity<>("Record Deleted Successfully",HttpStatus.NO_CONTENT);
	}

}
 
