package com.synacy.leaveapplication.employee;

import com.synacy.leaveapplication.Manager.Manager;
import com.synacy.leaveapplication.Manager.ManagerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Manager manager = managerService.getManagerByName(employeeDetails.getManagerName());
        Employee employee = new Employee (employeeDetails.getName(),employeeDetails.getHireDate(),employeeDetails.getTotalLeave(),manager);
        return employeeRepository.save(employee);

    }
    public List<Employee> findAllEmployees (){
        return employeeRepository.findAll();
    }



    public  Employee updateEmployee(Long id, String name, int totalLeave, int currentLeave){
        Optional <Employee> employeeOptional = employeeRepository.findEmployeeById(id);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setName(name);
            employee.setTotalLeaves(totalLeave);
            employee.setCurrentLeave(currentLeave);
            return employeeRepository.save(employee);

        }
        throw new IllegalArgumentException ();
        }

//    public Employee updateEmployee(EmployeeDetails employeeDetails) {
//        Optional <Employee> employeeOptional = employeeRepository.findEmployeeById(id);
//
//
//    }
}

