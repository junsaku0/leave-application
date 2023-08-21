package com.synacy.leaveapplication.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping ("/api/v1/employee")
    public ResponseEntity<Employee> addEmployee (@RequestBody EmployeeDetails employeeDetails) {
        Employee newEmployee = employeeService.createEmployee(employeeDetails);
        return ResponseEntity.ok(newEmployee);
    }
}
