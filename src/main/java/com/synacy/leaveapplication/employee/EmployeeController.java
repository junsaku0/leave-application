package com.synacy.leaveapplication.employee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDetails employeeDetails) {
        Employee newEmployee = employeeService.createEmployee(employeeDetails);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/api/v1/user/employee")
    public ResponseEntity<List<EmployeeUserResponse>> fetchEmployeeUserList() {
        List<Employee> employeeList = employeeService.findAllEmployees();

        List<EmployeeUserResponse> employeeResponseList = employeeList.stream().map(EmployeeUserResponse::new).collect(Collectors.toList());

        return new ResponseEntity<>(employeeResponseList, HttpStatus.OK);
    }

}
