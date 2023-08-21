package com.synacy.leaveapplication.employee;

import com.synacy.leaveapplication.Manager.Manager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Employee {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)

    @Getter
    @Setter
   private String name;

    @Getter
    @Setter
   private Date hireDate;
    @Getter
    @Setter
    private int totalLeaves;

    @Getter
    @Setter
   @OneToOne
    @JoinColumn (name = "manager_Id")
    public Manager manager;

    @Getter
    @Setter
    int currentLeave;

    public Employee(String name, Date hireDate, int totalLeaves, Manager manager) {
     this.name = name;
     this.hireDate = hireDate;
     this.totalLeaves = totalLeaves;
     this.manager= manager;
     this.currentLeave = totalLeaves;

    }
    public Employee () {

    }
}
