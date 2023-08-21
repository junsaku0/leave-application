package com.synacy.leaveapplication.Manager;

import lombok.Getter;

import java.util.Date;

public class ManagerDetails {


    @Getter
    private String name;
    @Getter

    private Date hireDate;
    @Getter

    private int totalLeave;
    @Getter

    private int currentLeave;

    public ManagerDetails(String name, Date hireDate, int totalLeave) {
        this.name = name;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
    }
}


