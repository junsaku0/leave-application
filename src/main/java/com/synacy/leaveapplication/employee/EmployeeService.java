package com.synacy.leaveapplication.employee;

import com.synacy.leaveapplication.Manager.Manager;
import com.synacy.leaveapplication.Manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private ManagerService managerService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ManagerService managerService) {
        this.employeeRepository = employeeRepository;
        this.managerService = managerService;
    }
    public Employee createEmployee(EmployeeDetails employeeDetails) {
        Manager manager = managerService.getManagerById(employeeDetails.getManagerId());
        Employee employee = new Employee (employeeDetails.getName(),employeeDetails.getHireDate(),employeeDetails.getTotalLeave(),manager);
        return employeeRepository.save(employee);

    }
}