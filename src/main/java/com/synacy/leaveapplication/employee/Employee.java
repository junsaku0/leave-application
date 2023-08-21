package com.synacy.leaveapplication.employee;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    String name;
    @Column (nullable = false)
    Date hireDate;
    @Column (nullable = false)
    int totalNumberOfLeaves;
    @Column (nullable = false)
    int managerId;
    @Column (nullable = false)
    int currentNumberOfLeaves;

    public Employee(String name, Date hireDate, int totalNumberOfLeaves, int managerId, int currentNumberOfLeaves) {
     this.name = name;
     this.hireDate = hireDate;
     this.totalNumberOfLeaves = totalNumberOfLeaves;
     this.managerId = managerId;
     this.currentNumberOfLeaves = currentNumberOfLeaves;

    }







}
