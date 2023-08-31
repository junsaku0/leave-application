package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDetails {


    @Getter
    private String name;

    private UserRole role;

    private String head;

   private LocalDate hireDate;

    private int totalLeave;


    public UserDetails(String name, UserRole role, String head,
                       LocalDate hireDate, int totalLeave) {
        this.name = name;
        this.role = role;
        this.head = head;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
    }
}
