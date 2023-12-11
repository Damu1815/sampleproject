package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
