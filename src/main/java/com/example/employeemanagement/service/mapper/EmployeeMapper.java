package com.example.employeemanagement.service.mapper;

import com.example.employeemanagement.datacontract.EmployeeDto;
import com.example.employeemanagement.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    // Convert Employee to EmployeeDTO
    public EmployeeDto toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDto dto = new EmployeeDto();
        dto.setFullName(employee.getFullName());
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setJobTitle(employee.getJobTitle());
        dto.setDepartment(employee.getDepartment());
        dto.setHireDate(employee.getHireDate());
        dto.setEmploymentStatus(employee.getEmploymentStatus());
        dto.setContactInformation(employee.getContactInformation());
        dto.setAddress(employee.getAddress());

        return dto;
    }

    public List<EmployeeDto> toDtos(List<Employee> employees) {
        return employees.stream().map(this::toDto).toList();
    }

    // Convert EmployeeDTO to Employee
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setJobTitle(dto.getJobTitle());
        employee.setDepartment(dto.getDepartment());
        employee.setHireDate(dto.getHireDate());
        employee.setEmploymentStatus(dto.getEmploymentStatus());
        employee.setContactInformation(dto.getContactInformation());
        employee.setAddress(dto.getAddress());

        return employee;
    }
}
