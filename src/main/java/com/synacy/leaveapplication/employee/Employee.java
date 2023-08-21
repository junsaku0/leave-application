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
    @ManyToOne(targetEntity = Manager.class, cascade = {CascadeType.ALL})
    @JoinColumn (name = "manager_Id")
    private Long managerId;

    @Getter
    @Setter
    int currentLeave;

    public Employee(String name, Date hireDate, int totalLeaves, Long managerId) {
     this.name = name;
     this.hireDate = hireDate;
     this.totalLeaves = totalLeaves;
     this.managerId = managerId;
     this.currentLeave = totalLeaves;

    }
    public Employee () {

    }
}
