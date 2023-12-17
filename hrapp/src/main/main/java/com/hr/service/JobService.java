package com.hr.service;
 
import java.util.List;
 
import com.hr.entity.Job;
 
public interface JobService {
	boolean saveJob(Job j);
	boolean modifyJob(Job j);
	boolean updateSalary(Job job,Long minSal, Long maxSal);
	void deleteJobById(String jobId);
	List<Job>getAllJobs();
 
}