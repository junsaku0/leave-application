package com.synacy.leaveapplication.leave;

import com.synacy.leaveapplication.UserRole;
import lombok.Getter;

import java.time.LocalDate;

public class LeaveDetails {

    @Getter
    Long userId;

    @Getter
    String name;

    @Getter
    UserRole role;

    @Getter
    LocalDate startDate;

    @Getter
    LocalDate endDate;

    @Getter
    String reason;

    public LeaveDetails(Long userId, String name, UserRole role, LocalDate startDate,
                        LocalDate endDate, String reason) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

}
