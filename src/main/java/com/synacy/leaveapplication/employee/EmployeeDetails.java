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
    private String managerName;

    @Getter
    private int currentLeave;

    public EmployeeDetails(String name,int totalLeave,String managerName){
        this.name = name;
        this.totalLeave = totalLeave;
        this.managerName= managerName;

    }
}
