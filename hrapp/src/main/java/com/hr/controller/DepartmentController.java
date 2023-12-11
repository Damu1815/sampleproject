package com.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.entity.Department;
import com.hr.repository.DepartmentRepository;
@RestController
public class DepartmentController {
	   @Autowired
	    private DepartmentRepository departmentRepository;
	    
	   @GetMapping("/alldepartments")
	   public List<Department> getAll(){
		   return departmentRepository.findAll();
	   }
}
