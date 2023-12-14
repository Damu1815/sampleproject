package com.hr.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.Department;
import com.hr.entity.Employee;
import com.hr.entity.Job;
import com.hr.exception.DuplicateEmployeeException;
import com.hr.exception.EmployeeNotFoundException;
import com.hr.repository.DepartmentRepository;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.JobRepository;
import com.hr.util.ErrorResponse;
@Service
public class EmployeeServiceImpl implements EmployeeService{
  
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JobRepository jobRepositroy;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public boolean saveEmployee(Employee e) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isPresent()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Already Exists");
			throw new DuplicateEmployeeException(response);
		}
		employeeRepository.save(e);
		return true;
	}

	@Override
	public boolean modifyEmployee(Employee e) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		employeeRepository.save(e);
		return true;
		
	}

	@Override
	public boolean updateEmployeeJobId(Employee e, String jobId) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Optional<Job> opt1 = jobRepositroy.findById(jobId);
		if(opt1.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Job Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		Job job = opt1.get();
		employee.setJob(job);
		employeeRepository.save(employee);
		return true;
		
	}

	@Override
	public boolean assignEmployeeManager(Employee e, Long managerId) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Optional<Employee> opt1 = employeeRepository.findById(managerId);
		if(opt1.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Manager Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		employee.setManager(opt1.get());
		employeeRepository.save(employee);
		return true;
	}

	@Override
	public boolean assignEmployeeDepartment(Employee e, Long departmentId) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Optional<Department> opt1 = departmentRepository.findById(departmentId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Department Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		employee.setDepartment(opt1.get());
		employeeRepository.save(employee);
		return true;
	}

	@Override
	public boolean updateEmployeeEmail(String email) {
		
		return false;
	}
	
}
