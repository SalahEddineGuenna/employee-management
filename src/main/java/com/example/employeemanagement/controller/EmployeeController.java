package com.example.employeemanagement.controller;


import com.example.employeemanagement.datacontract.EmployeeDto;
import com.example.employeemanagement.entities.Employee;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeMapper.toDtos(employeeService.getAllEmployees());
        return ResponseEntity.ok(employeeDtos); // 200 OK
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
        EmployeeDto employeeDto = employeeMapper.toDto(employeeService.getEmployeeById(id));
        return ResponseEntity.ok(employeeDto); // 200 OK
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        EmployeeDto savedEmployeeDto = employeeMapper.toDto(employeeService.addEmployee(employee));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeeDto); // 201 Created
    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        EmployeeDto updatedEmployeeDto = employeeMapper.toDto(employeeService.updateEmployee(id, employee));
        return ResponseEntity.ok(updatedEmployeeDto); // 200 OK
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping(value = "/employees/search")
    public ResponseEntity<List<EmployeeDto>> searchEmployee(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String jobTitle) {

        List<EmployeeDto> employees = employeeMapper.toDtos(employeeService.searchEmployees(fullName, employeeId, department, jobTitle));
        return ResponseEntity.ok(employees); // 200 OK
    }

}