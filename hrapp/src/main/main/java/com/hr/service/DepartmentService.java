package com.hr.service;

import java.util.List;

import com.hr.entity.Department;

public interface DepartmentService {
	public void addNewDepartment(Department department);
	public void updateDepartment(Department department);
	public Object findMaxSalaryInDepartment(Long departmentId);
	public Object findMinSalaryInDepartment(Long departmentId);
	public List<Department> getAllDepartmentsEmployeeWorked(Long empid);
	public boolean deleteDepartmentById(Long departmentId);
}
