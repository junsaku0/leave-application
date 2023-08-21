package com.synacy.leaveapplication.Manager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @Setter
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Date hireDate;
    @Getter
    @Setter
    private int totalLeave;
    @Getter
    @Setter
    private int  currentLeave;

    public Manager(String name, Date hireDate, int totalLeave) {
        this.name = name;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
        this.currentLeave = totalLeave;
    }
    public Manager() {

    }
}
