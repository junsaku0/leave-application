package com.synacy.leaveapplication.user;

import com.synacy.leaveapplication.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
public class Users {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    String name;

    @Getter
    @Setter
    @Column(nullable = false)
    UserRole role;

    @Getter
    @Setter
    @Column(nullable = false)
    Long headId;

    @Getter
    @Setter
    @Column(nullable = false)
    LocalDate hireDate;

    @Getter
    @Setter
    @Column(nullable = false)
    Integer totalLeaves;

    @Getter
    @Setter
    Integer earnedLeave;

    public Users(String name, UserRole role,
                 Long headId, LocalDate hireDate,
                 Integer totalLeaves) {
        this.name = name;
        this.role = role;
        this.headId = headId;
        this.hireDate = hireDate;
        this.totalLeaves = totalLeaves;
        this.earnedLeave = 0;
    }

    public Users() {

    }
}
