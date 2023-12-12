package com.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

//	@Query("SELECT d.departmentName, MAX(e.salary) FROM Employee e JOIN e.department d WHERE d.departmentId = :departmentId GROUP BY d.departmentName")
	 Object findMaxSalaryInDepartment(@Param("departmentId") Long departmentId);
	 
//	 @Query("SELECT d.departmentName, MIN(e.salary) FROM Employee e JOIN e.department d WHERE d.departmentId = :departmentId GROUP BY d.departmentName")
	 Object findMinSalaryInDepartment(@Param("departmentId") Long departmentId);
	 
	 
	  List<Department> getAllDepartmentsEmployeeWorkedByEmployeeId(Long empid);
}
