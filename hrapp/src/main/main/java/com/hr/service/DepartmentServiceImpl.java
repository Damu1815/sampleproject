package com.hr.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.Department;
import com.hr.exception.DepartmentNotFoundException;
import com.hr.repository.DepartmentRepository;
import com.hr.util.ErrorResponse;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public void addNewDepartment(Department department) {
		
		Optional<Department> optionalDepartment = departmentRepository.findById(department.getDepartmentId());
		if(optionalDepartment.isPresent()) {
			departmentRepository.save(department);
			
		}else {
		ErrorResponse response = new ErrorResponse(LocalDate.now(),"DepartmentNotFound");
			throw new DepartmentNotFoundException(response);
		}
	}

	@Override
	public void updateDepartment(Department department) {
		
		Optional<Department> optionalDepartment = departmentRepository.findById(department.getDepartmentId());
		if(optionalDepartment.isPresent()) {
			departmentRepository.save(department);
			
		}else {
		ErrorResponse response = new ErrorResponse(LocalDate.now(),"DepartmentNotFound");
			throw new DepartmentNotFoundException(response);
		}
	}

	@Override
	public Object findMaxSalaryInDepartment(Long departmentId) {
		
		return departmentRepository.findMaxSalaryInDepartment(departmentId);
	}

	@Override
	public Object findMinSalaryInDepartment(Long departmentId) {
		
		return departmentRepository.findMinSalaryInDepartment(departmentId);
	}

	@Override
	public List<Department> getAllDepartmentsEmployeeWorked(Long empid) {
		
		return departmentRepository.getAllDepartmentsEmployeeWorkedByEmployeeId(empid);
	}

	@Override
	public boolean deleteDepartmentById(Long departmentId) {
		
			departmentRepository.deleteById(departmentId);
			return true;
		
	}

}
 
