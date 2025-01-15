package com.example.employeemanagement.service;

import com.example.employeemanagement.entities.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("securityService")
@NoArgsConstructor
public class SecurityService {


    public boolean hasManagerDepartmentAccess(String employeeDepartment) {
        // Get the currently authenticated user
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Retrieve the department of the current manager
        String managerDepartment = currentUser.getDepartment();  // Implement this method in your service

        // Return true if the current user's department matches the employee's department
        return managerDepartment != null && managerDepartment.equals(employeeDepartment);
    }
}
