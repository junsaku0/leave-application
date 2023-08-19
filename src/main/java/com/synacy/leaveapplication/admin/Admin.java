package com.synacy.leaveapplication.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @Getter
    Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    String name;

    public Admin(String name) {
        this.name = name;
    }

    public Admin() {

    }
}
