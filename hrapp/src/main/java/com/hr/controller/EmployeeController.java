package com.hr.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Employee;
import com.hr.repository.EmployeeRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping()
	public Collection<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/findfname/{firstname}")
	public ResponseEntity<List<Employee>> getByFirstName(@PathVariable("firstname")String firstName) {
		List<Employee>  employeeList= employeeRepository.findByFirstName(firstName);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("/findemail/{email}")
	public ResponseEntity<Employee> getByEmail(@Valid @PathVariable String email ){
		Employee employee = employeeRepository.findByEmail(email);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@GetMapping("/findphone/{phoneNumber}")
	public ResponseEntity<Employee> getByPhoneNo(@Valid @PathVariable String phoneNumber ){
		Employee employee = employeeRepository.findByPhoneNumber(phoneNumber);
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
	@GetMapping("/findAllEmployeeWithNoCommission")
	public ResponseEntity<List<Employee>> getAllEmployeeWithNoCommission() {
		List<Employee>  employeeList= employeeRepository.findAllByCommissionPctIsNull();
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("listAllEmployees/{department_id}")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable Long departmentId) {
		List<Employee>  employeeList= employeeRepository.findAllByDepartmentDepartmentId(departmentId);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	@GetMapping("countEmployeesByDepartment")
    public ResponseEntity<List<Object[]>> countAllEmployeesGroupByDepartment() {
        List<Object[]> result = employeeRepository.countAllEmployeesGroupByDepartmentDepartmentId();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@GetMapping("listallemployeebyhiredate/{fromHireDate}/{toHireDate}")
	public ResponseEntity<List<Employee>> getAllEmployeesByHireDateRange
	   (@PathVariable LocalDate fromHireDate,@PathVariable LocalDate toHireDate){
		List<Employee>  employeeList= employeeRepository.findAllByHireDateBetween(fromHireDate,toHireDate);
		return new ResponseEntity<>(employeeList,HttpStatus.OK);
	}
	
	/*@GetMapping("listallmangerdetails")
	public ResponseEntity<List<Employee>> getAllManagerDetails(){
		List<Employee> employeeList = employeeRepository.findAllManagers();
		return new ResponseEntity<>(employeeList,HttpStatus.OK); 
	}*/
	
	/*@GetMapping("locationwisecountofemployees")
    public ResponseEntity<List<Object[]>> countAllEmployeesGroupByLocation() {
        List<Object[]> result = employeeRepository.countAllEmployeesGroupByLocationId();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/
	
	@GetMapping("/{employeeId}/findmaxsalaryofjob")
	public ResponseEntity<List<Object[]>> findMaxSalaryOfJobByEmployeeId(@PathVariable Long employeeId){
		List<Object[]> result = employeeRepository.findMaxSalaryOfJobByemployeeId(employeeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    
}
