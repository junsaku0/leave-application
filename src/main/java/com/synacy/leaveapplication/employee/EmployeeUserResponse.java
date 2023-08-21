package com.synacy.leaveapplication.employee;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;


@Getter
public class EmployeeUserResponse {

    private final Long id;

    @Getter
    private String name;

    @Getter
    private UserRole role;




    public EmployeeUserResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.role = UserRole.EMPLOYEE;
    }
}

