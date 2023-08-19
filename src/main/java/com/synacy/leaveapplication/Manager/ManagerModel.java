package com.synacy.leaveapplication.Manager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
public class ManagerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @Setter
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

    public ManagerModel(Long id, String name, Date hireDate, int totalLeave, int currentLeave) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
        this.totalLeave = totalLeave;
        this.currentLeave = currentLeave;
    }
    public ManagerModel() {

    }
}
