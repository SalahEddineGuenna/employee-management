package com.example.employeemanagement.datacontract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String fullName;

    private String employeeId;

    private String jobTitle;

    private String department;

    private LocalDate hireDate;

    private String employmentStatus;

    private String contactInformation;

    private String address;
}
