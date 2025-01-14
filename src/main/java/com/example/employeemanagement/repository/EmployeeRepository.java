package com.example.employeemanagement.repository;

import com.example.employeemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT e FROM Employee e " +
            "WHERE (:fullName IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) " +
            "AND (:employeeId IS NULL OR e.employeeId = :employeeId) " +
            "AND (:department IS NULL OR LOWER(e.department) LIKE LOWER(CONCAT('%', :department, '%'))) " +
            "AND (:jobTitle IS NULL OR LOWER(e.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%')))")
    List<Employee> searchEmployee(@Param("fullName") String fullName,
                                  @Param("employeeId") String employeeId,
                                  @Param("department") String department,
                                  @Param("jobTitle") String jobTitle);
}
