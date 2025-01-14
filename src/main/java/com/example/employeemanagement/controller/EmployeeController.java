package com.example.employeemanagement.controller;


import com.example.employeemanagement.datacontract.EmployeeDto;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @GetMapping(value = "/employees")
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtos(employeeService.getAllEmployees());
    }
}
