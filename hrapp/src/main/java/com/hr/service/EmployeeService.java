package com.hr.service;

import com.hr.entity.Employee;

public interface EmployeeService {
  
	boolean saveEmployee(Employee e);
	boolean modifyEmployee(Employee e);
	boolean updateEmployeeJobId(Employee e,String jobId);
	boolean assignEmployeeManager(Employee e,Long managerId);
	boolean assignEmployeeDepartment(Employee e,Long departmentId);
	boolean updateEmployeeEmail(String email);
	
}
