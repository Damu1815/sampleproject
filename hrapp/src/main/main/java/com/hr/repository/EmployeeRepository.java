package com.hr.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.Employee;
import com.hr.entity.Job;

import jakarta.validation.Valid;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findByFirstName(String firstName);

	Employee findByEmail(@Valid String email);

	Employee findByPhoneNumber(@Valid String phoneNumber);

	List<Employee> findAllByCommissionPctIsNull();

	List<Employee> findAllByDepartmentDepartmentId(Long departmentId);

	List<Employee> findAllByHireDateBetween(LocalDate fromHireDate, LocalDate toHireDate);

	@Query("SELECT e.department.departmentName department, COUNT(e) count FROM Employee e GROUP BY e.department.departmentId")
	List<Object[]> countAllEmployeesGroupByDepartmentDepartmentId();
    
	@Query("select e.department.location , count(e) from Employee e group by e.department.location")
	List<Object[]> countAllEmployeesGroupByLocation();
  
	@Query("SELECT DISTINCT j FROM Job j LEFT JOIN Employee e ON j.jobId = e.job.jobId AND e.department.departmentId = :departmentId WHERE e.employeeId IS NULL")
    List<Job> findAllOpenPositionsInDepartment(@Param("departmentId") Long departmentId);
    
	List<Employee> findAllByManagerIsNull();
	
	@Query("SELECT e.department.departmentId,COALESCE(SUM(e.salary), 0)*COALESCE(SUM(e.commissionPct), 0)  FROM Employee e WHERE e.department.departmentId = :departmentId")
	List<Object[]> findTotalCommissionIssuedToSalaryForDepartment(@Param("departmentId") Long departmentId);

	 @Query("SELECT DISTINCT j FROM Job j WHERE j.jobId NOT IN (SELECT e.job.jobId FROM Employee e)")
	 List<Job> findAllOpenPositions();
	 
	 
}
