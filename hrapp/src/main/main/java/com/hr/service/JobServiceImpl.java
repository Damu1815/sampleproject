package com.hr.service;
 
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.hr.entity.Job;
import com.hr.exception.DuplicateJobException;
import com.hr.exception.JobNotFoundException;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.JobRepository;
import com.hr.util.ErrorResponse;
 
@Service
public class JobServiceImpl implements JobService{
	@Autowired
	private JobRepository jobRepository;

	@Override
	public boolean saveJob(Job j) {
		Optional<Job> opt = jobRepository.findById(j.getJobId());
		if(opt.isPresent()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Already Exists");
			throw new DuplicateJobException(response);
		}
		jobRepository.save(j);
		return true;
	}
 
	@Override
	public boolean modifyJob(Job j) {
		Optional<Job> opt = jobRepository.findById(j.getJobId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Not Found");
			throw new JobNotFoundException(response);
		}
		jobRepository.save(j);
		return true;
	}
 
	
	@Override
	public void deleteJobById(String jobId) {
		Optional<Job> opt = jobRepository.findById(jobId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Not Found");
			throw new JobNotFoundException(response);
		}
		jobRepository.delete(opt.get());
	}
 
	@Override
	public List<Job> getAllJobs() {
		List<Job> jobs = jobRepository.findAll();
		if(jobs.isEmpty() || jobs==null) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Not Found");
			throw new JobNotFoundException(response);
 
		}
		return jobRepository.findAll();
	}
 
	@Override
	public boolean updateSalary(Job job, Long minSal, Long maxSal) {
		Optional<Job> opt = jobRepository.findById(job.getJobId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Not Found");
			throw new JobNotFoundException(response);
		}
		Job job1 = opt.get();
		job1.setMaxSalary(maxSal);
		job1.setMinSalary(minSal);
		jobRepository.save(job);
		return true;
	}
 
 
}
