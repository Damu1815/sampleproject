package com.hr.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.mapping.Map;
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
import com.hr.entity.Employee;
import com.hr.entity.Job;
import com.hr.repository.EmployeeRepository;
import com.hr.service.EmployeeService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path="/api/v1/employees" ,produces = "application/json")
public class EmployeeController {


	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;


	@GetMapping()
	public Collection<Employee> getAll() {
		return employeeService.getAll();
	}
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable Long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}
	@GetMapping("/findfname/{firstname}")
	public ResponseEntity<List<Employee>> getByFirstName(@PathVariable("firstname")String firstName) {
		List<Employee>  employeeList= employeeService.getByFirstName(firstName);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("/findemail/{email}")
	public ResponseEntity<Employee> getByEmail(@Valid @PathVariable String email ){
		Employee employee = employeeService.getByEmail(email);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@GetMapping("/findphone/{phoneNumber}")
	public ResponseEntity<Employee> getByPhoneNo(@Valid @PathVariable String phoneNumber ){
		Employee employee = employeeService.getByPhoneNo(phoneNumber);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@GetMapping("/findAllEmployeeWithNoCommission")
	public ResponseEntity<List<Employee>> getAllEmployeeWithNoCommission() {
		List<Employee>  employeeList= employeeService.getAllEmployeeWithNoCommission();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("/listAllEmployees/{departmentId}")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable Long departmentId) {
		List<Employee>  employeeList= employeeService.getAllEmployeesByDepartment(departmentId);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
    
	@GetMapping("/employees_department_wisecount")
	public ResponseEntity<List<Object[]>> countAllEmployeesGroupByDepartment(){
		List<Object[]>  employeeList= employeeService.countAllEmployeesGroupByDepartment();
		return new ResponseEntity<List<Object[]>>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("listallmanagerdetails")
	public ResponseEntity<List<Employee>> getAllManagers() {
		List<Employee>  employeeList= employeeService.getAllManagers();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("/locationwisecountofemployees")
	public ResponseEntity<List<Object[]>> countAllEmployeesGroupByLocation() {
		List<Object[]>  employeeList= employeeService.countAllEmployeesGroupByLocation();
		return new ResponseEntity<List<Object[]>>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("/{employeeId}/findmaxsalaryofjob")
	public ResponseEntity<Double> findMaxSalaryOfJobByEmployeeId(@PathVariable Long employeeId){
		double maxSalary = employeeService.findMaxSalaryOfJobByEmployeeId(employeeId);
		return new ResponseEntity<> (maxSalary,HttpStatus.OK);
	}
	
	@GetMapping("/findallopenpositions/{departmentId}")
	public ResponseEntity<List<Job>> findAllOpenPositionsByDepartment(@PathVariable("departmentId") Long departmentId) {
		List<Job>  jobList= employeeService.allOpenPositionsInDepartment(departmentId);
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}
	
	@GetMapping("/findallopenpositions")
	public ResponseEntity<List<Job>> getAllOpenPositions() {
		List<Job>  jobList= employeeService.allOpenPositions();
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}
	
	@GetMapping("/totalcommissionissuedtodepartment/{departmentId}")
	public ResponseEntity<List<Object[]>> totalCommissionIssuedDepartment(@PathVariable Long departmentId){
		List<Object[]> totalCommision = employeeService.totalCommisionIssuedToDepartment(departmentId);
		return new ResponseEntity<>(totalCommision,HttpStatus.OK);
	}
	
	
	@GetMapping("/listallemployeebyhiredate/{fromHireDate}/{toHireDate}")
	public ResponseEntity<List<Employee>> getAllEmployeesByHireDateRange
	   (@PathVariable LocalDate fromHireDate,@PathVariable LocalDate toHireDate){
		List<Employee> employeeList= employeeService.getAllEmployeesByHireDateRange(fromHireDate,toHireDate);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@PostMapping("/addEmployee")
	ResponseEntity<String> addEmployee(@RequestBody Employee employee){
	 employeeService.saveEmployee(employee);
	 return new ResponseEntity<>("Record Created Successfully",HttpStatus.CREATED);
		
	}
	
	@PutMapping()
	ResponseEntity<String> modifyEmployee(@Valid @RequestBody Employee employee){
		 employeeService.modifyEmployee(employee);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);	
		}
	
	@PutMapping("assignjob/{jobId}")
	ResponseEntity<String> assignEmployeeJob(@Valid @RequestBody Employee employee, @PathVariable String jobId){
		 employeeService.updateEmployeeJobId(employee,jobId);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);
			
		}
	
	@PutMapping("assignmanager/{managerId}")
	ResponseEntity<String> assignEmployeeManager(@Valid @RequestBody Employee employee, @PathVariable Long managerId){
		 employeeService.assignEmployeeManager(employee,managerId);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);
			
		}
	
	@PutMapping("assigndepartment/{departmentId}")
	ResponseEntity<String> assignEmployeeDepartment(@Valid @RequestBody Employee employee, @PathVariable Long departmentId){
		 employeeService.assignEmployeeDepartment(employee,departmentId);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);
			
		}
	
	@PutMapping("assignemail/{email}")
	ResponseEntity<String> updateEmployeeEmail(@Valid @RequestBody Employee employee, @PathVariable String email){
		 employeeService.updateEmployeeEmail(employee,email);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);
	    }
	
	@PutMapping("assignphoneno/{phoneNumber}")
	ResponseEntity<String> updateEmployeePhoneNumber(@Valid @RequestBody Employee employee, @PathVariable String phoneNumber){
		 employeeService.updateEmployeePhoneNumber(employee,phoneNumber);
		 return new ResponseEntity<>("Record Modified Successfully",HttpStatus.OK);
	    }
	@DeleteMapping("delete/{employeeId}")
	ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId){
		 employeeService.deleteEmployee(employeeId);
		 return new ResponseEntity<>("Record deleted Successfully",HttpStatus.OK);
	    }
	
}
