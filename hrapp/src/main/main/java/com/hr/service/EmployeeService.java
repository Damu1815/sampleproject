package com.hr.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hr.entity.Employee;
import com.hr.entity.Job;

public interface EmployeeService {
  
	boolean saveEmployee(Employee employee);
	boolean modifyEmployee(Employee employee);
	boolean updateEmployeeJobId(Employee employee,String jobId);
	boolean assignEmployeeManager(Employee employee,Long managerId);
	boolean assignEmployeeDepartment(Employee employee,Long departmentId);
	boolean updateEmployeeEmail(Employee employee,String email);
	boolean updateEmployeePhoneNumber(Employee employee,String phoneNumber);
	boolean deleteEmployee(Long employeeId);
	Collection<Employee> getAll();
	List<Employee> getByFirstName(String firstName);
	Employee getByEmail(String email);
	Employee getByPhoneNo(String phoneNumber);
	List<Employee> getAllEmployeeWithNoCommission();
	List<Employee> getAllEmployeesByDepartment(Long departmentId);
	List<Object[]> countAllEmployeesGroupByDepartment();
	List<Object[]> countAllEmployeesGroupByLocation();
	List<Employee> getAllEmployeesByHireDateRange(LocalDate from, LocalDate to);
	List<Employee> getAllManagers();	
	List<Object[]> totalCommisionIssuedToDepartment(Long departmentId);
	List<Job> allOpenPositionsInDepartment(Long departmentId);
	List<Job> allOpenPositions();
	double findMaxSalaryOfJobByEmployeeId(Long EmployeeId);
}
