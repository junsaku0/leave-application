package com.synacy.leaveapplication.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(EmployeeDetails employeeDetails) {
        Employee employee = new Employee (employeeDetails.getName(),employeeDetails.getHireDate(),employeeDetails.getTotalLeave(),employeeDetails.getManagerId());
        return employeeRepository.save(employee);

    }
}