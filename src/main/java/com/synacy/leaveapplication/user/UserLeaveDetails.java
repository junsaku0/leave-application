package com.synacy.leaveapplication.user;

import lombok.Getter;

public class UserLeaveDetails {

    @Getter
    Integer totalLeave;

    @Getter
    Integer earnedLeave;

    public UserLeaveDetails(int totalLeave, int earnedLeave) {
        this.totalLeave = totalLeave;
        this.earnedLeave = earnedLeave;
    }
}
