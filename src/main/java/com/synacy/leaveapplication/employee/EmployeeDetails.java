package com.synacy.leaveapplication.employee;

import lombok.Getter;

import java.util.Date;

@Getter
public class EmployeeDetails {

    private String name;


    private Date hireDate;

    private int totalLeave;

    private String manager;

    private int currentLeave;

    public EmployeeDetails(String name, Date hireDate, int totalLeave, String manager) {
        this.name = name;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
        this.manager = manager;

    }
}
