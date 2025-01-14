package com.example.employeemanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Audited
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "department")
    private String department;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "employment_status")
    private String employmentStatus;

    @Column(name = "contat_informations")
    private String contactInformation;

    @Column(name = "address")
    private String address;

    public boolean update(Employee copy) {
        if (copy == null) {
            return false; // No update if the incoming object is null
        }

        boolean updated = false;

        if (!StringUtils.equals(copy.getFullName(), this.fullName)) {
            this.fullName = copy.getFullName();
            updated = true;
        }
        if (!StringUtils.equals(copy.getEmployeeId(), this.employeeId)) {
            this.employeeId = copy.getEmployeeId();
            updated = true;
        }
        if (!StringUtils.equals(copy.getJobTitle(), this.jobTitle)) {
            this.jobTitle = copy.getJobTitle();
            updated = true;
        }
        if (!StringUtils.equals(copy.getDepartment(), this.department)) {
            this.department = copy.getDepartment();
            updated = true;
        }
        if (!Objects.equals(copy.getHireDate(), this.hireDate)) {
            this.hireDate = copy.getHireDate();
            updated = true;
        }
        if (!StringUtils.equals(copy.getEmploymentStatus(), this.employmentStatus)) {
            this.employmentStatus = copy.getEmploymentStatus();
            updated = true;
        }
        if (!StringUtils.equals(copy.getContactInformation(), this.contactInformation)) {
            this.contactInformation = copy.getContactInformation();
            updated = true;
        }
        if (!StringUtils.equals(copy.getAddress(), this.address)) {
            this.address = copy.getAddress();
            updated = true;
        }

        return updated; // Returns true if any field was updated
    }


}
