package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Employee;
import com.hr.repository.EmployeeRepository;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/all")
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	@GetMapping("/emp/{id}")
	public Employee getByEId(@PathVariable Long id) {
		return employeeRepository.findById(id).get();
	}
	
	@PostMapping("/save")
	public Employee saveEmp(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@PostMapping("/save1")
	public Employee getEmp(@RequestBody Employee employee) {
		System.out.println(employee.getEmail());
		return employee;
	}

}
