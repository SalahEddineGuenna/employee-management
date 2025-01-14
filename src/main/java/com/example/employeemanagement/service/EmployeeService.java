package com.example.employeemanagement.service;


import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employeeToUpdate = employeeOptional.get();
            if (employeeToUpdate.update(employee)) {
                return employeeRepository.save(employee);
            }
        }

        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployees(String fullName, String employeeId, String department, String jobTitle) {
        return employeeRepository.searchEmployee(fullName, employeeId, department, jobTitle);
    }
}
