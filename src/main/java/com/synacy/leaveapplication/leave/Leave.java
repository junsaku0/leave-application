package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leave_sequence")
    @SequenceGenerator(name = "leave_sequence", sequenceName = "leave_sequence", allocationSize = 1)
    @Getter
    Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    Long userId;

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
    LocalDate fileDate;

    @Getter
    @Setter
    @Column(nullable = false)
    LocalDate startDate;

    @Getter
    @Setter
    @Column(nullable = false)
    LocalDate endDate;

    @Getter
    @Setter
    @Column(nullable = false)
    Long duration;

    @Getter
    @Setter
    @Column(nullable = false)
    LeaveStatus status;

    @Getter
    @Setter
    @Column(nullable = false)
    String reason;

    public Leave(Long userId, String name, UserRole role, LocalDate startDate,
                 LocalDate endDate, String reason) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.fileDate = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = ChronoUnit.DAYS.between(startDate, endDate);
        this.status = LeaveStatus.PENDING;
        this.reason = reason;
    }

    public Leave() {

    }
}
