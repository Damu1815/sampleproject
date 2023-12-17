package com.hr.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.Department;
import com.hr.entity.Employee;
import com.hr.entity.Job;
import com.hr.exception.DepartmentNotFoundException;
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
		employee.setJob(opt1.get());
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
		if(opt1.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Department Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		employee.setDepartment(opt1.get());
		employeeRepository.save(employee);
		return true;
	}

	@Override
	public boolean updateEmployeeEmail(Employee e,String email) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		employee.setEmail(email);
		employeeRepository.save(employee);
		return true;
	}
	
	@Override
	public boolean updateEmployeePhoneNumber(Employee e,String phoneNumber) {
		Optional<Employee> opt = employeeRepository.findById(e.getEmployeeId());
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		employee.setPhoneNumber(phoneNumber);
		employeeRepository.save(employee);
		return true;
	}

	@Override
	public boolean deleteEmployee(Long employeeId) {
		Optional<Employee> opt = employeeRepository.findById(employeeId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		employeeRepository.deleteById(employeeId);
		return true;
	}

	@Override
	public Collection<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getByFirstName(String firstName) {
		List<Employee> employeeList =  employeeRepository.findByFirstName(firstName);
		return employeeList;
	}

	@Override
	public Employee getByEmail(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if(employee==null) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
			return employee;
	}

	@Override
	public Employee getByPhoneNo(String phoneNumber) {
		Employee employee = employeeRepository.findByPhoneNumber(phoneNumber);
		if(employee==null) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
			return employee;
	}

	@Override
	public List<Employee> getAllEmployeeWithNoCommission() {
		List<Employee>  employeeList= employeeRepository.findAllByCommissionPctIsNull();
		return employeeList;
	}

	@Override
	public List<Employee> getAllEmployeesByDepartment(Long departmentId) {
		List<Employee>  employeeList= employeeRepository.findAllByDepartmentDepartmentId(departmentId);
		return employeeList;
	}

	@Override
	public List<Object[]> countAllEmployeesGroupByDepartment() {
		List<Object[]>  employeeList= employeeRepository.countAllEmployeesGroupByDepartmentDepartmentId();
		return employeeList;
	}

	@Override
	public List<Object[]> countAllEmployeesGroupByLocation() {
		List<Object[]>  employeeList= employeeRepository.countAllEmployeesGroupByLocation();
		return employeeList;
	}

	@Override
	public List<Employee> getAllEmployeesByHireDateRange(LocalDate from, LocalDate to) {
		List<Employee> employeeList= employeeRepository.findAllByHireDateBetween(from,to);
		return employeeList;
	}

	@Override
	public List<Employee> getAllManagers() {
		List<Employee> managerList = employeeRepository.findAllByManagerIsNull();
		return managerList;
	}

	@Override
	public List<Object[]> totalCommisionIssuedToDepartment(Long departmentId) {
		Optional<Department> opt = departmentRepository.findById(departmentId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Department Not Found");
			throw new DepartmentNotFoundException(response);
		}
		return employeeRepository.findTotalCommissionIssuedToSalaryForDepartment(departmentId);
	}

	@Override
	public List<Job> allOpenPositionsInDepartment(Long departmentId) {
		Optional<Department> opt = departmentRepository.findById(departmentId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Department Not Found");
			throw new DepartmentNotFoundException(response);
		}
		return employeeRepository.findAllOpenPositionsInDepartment(departmentId);
		
	}

	@Override
	public List<Job> allOpenPositions() {
		return employeeRepository.findAllOpenPositions();
	}

	@Override
	public double findMaxSalaryOfJobByEmployeeId(Long employeeId) {
		Optional<Employee> opt = employeeRepository.findById(employeeId);
		if(opt.isEmpty()) {
			ErrorResponse response = new ErrorResponse(LocalDate.now(),"Employee Not Found");
			throw new EmployeeNotFoundException(response);
		}
		Employee employee = opt.get();
		Job job = employee.getJob();
		
		Long maxs = jobRepositroy.findById(job.getJobId()).get().getMaxSalary();
		
		Double maxSalary =jobRepositroy.findById(job.getJobId()).get().getMaxSalary().doubleValue();
//				employeeRepository.findMaxSalaryByJobId(job.getJobId());
		return maxSalary;
	}
	
  
}
