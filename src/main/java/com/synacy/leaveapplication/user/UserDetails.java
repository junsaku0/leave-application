package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class UserDetails {

    private String name;

    private UserRole role;

    private Long headId;

    private LocalDate hireDate;

    private int totalLeave;


    public UserDetails(String name, UserRole role, Long headId,
                       LocalDate hireDate, int totalLeave) {
        this.name = name;
        this.role = role;
        this.headId = headId;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
    }
}
