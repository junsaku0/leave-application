package com.synacy.leaveapplication.employee

import spock.lang.Specification

class EmployeeServiceTest extends Specification{

    List<Employee> employeeList
    EmployeeService employeeService
    EmployeeRepository employeeRepository
    Employee employee

    void setup() {
        employeeList= new ArrayList<>()
        employee= new Employee("Precious","05/30/2023",15,1L)

        employeeList.add(employee)
        employeeService = new EmployeeService(employeeRepository)


        def "createEmployee should create employee with the correct details"() {

            given:







        }





}


}
