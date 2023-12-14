package com.hr.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hr.entity.Employee;

import jakarta.validation.Valid;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
 List<Employee> findByFirstName(String firstName);

 Employee findByEmail(@Valid String email);

 Employee findByPhoneNumber(@Valid String phoneNumber);

 List<Employee> findAllByCommissionPctIsNull();

 List<Employee> findAllByDepartmentDepartmentId(Long departmentId);

 List<Employee> findAllByHireDateBetween(LocalDate fromHireDate, LocalDate toHireDate);
 
 @Query("SELECT e.department.departmentName,COUNT(e) from employee e GROUP BY e.deparment.departmentId")
 List<Object[]> countAllEmployeesGroupByDepartmentDepartmentId();
 @Query("SELECT j.jobTitle, MAX(e.salary) FROM Employee e JOIN e.job j WHERE e.employeeId = :employeeId GROUP BY j.jobTitle")
 List<Object[]> findMaxSalaryOfJobByemployeeId(@Param("employeeId")Long employeeId);
 
 /*@Query("SELECT d.location.locationId, COUNT(e.employeeId) FROM Employee e JOIN e.department d GROUP BY d.location.locationId")
 List<Object[]> countAllEmployeesGroupByLocationId(); */
 
 


 
 
  
}
