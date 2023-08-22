package com.synacy.leaveapplication.employee;

import lombok.Getter;

import java.util.Date;

@Getter
public class EmployeeDetails {

    private String name;


    private Date hireDate;

    private int totalLeave;

    @Getter
    private String managerName;
    private String manager;

    private int currentLeave;

    public EmployeeDetails(String name,int totalLeave,String managerName){
        this.name = name;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
        this.managerName= managerName;

    }
}
