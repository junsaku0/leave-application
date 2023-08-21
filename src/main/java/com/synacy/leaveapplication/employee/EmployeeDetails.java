package com.synacy.leaveapplication.employee;

import lombok.Getter;

import java.util.Date;

@Getter
public class EmployeeDetails {

    private String name;


    private Date hireDate;

    @Getter
    private int totalLeave;

    @Getter
    private Long managerId;

    @Getter
    private int currentLeave;

    public EmployeeDetails(String name,int totalLeave,Long managerId) {
        this.name = name;
        this.totalLeave = totalLeave;
        this.managerId = managerId;

    }
}
