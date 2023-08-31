package com.synacy.leaveapplication.user;

import lombok.Getter;

public class UserLeaveDetails {
    @Getter
    Integer totalLeaves;
    @Getter
    Integer earnedLeave;

    public UserLeaveDetails(Integer totalLeaves, Integer earnedLeave) {
        this.totalLeaves = totalLeaves;
        this.earnedLeave = earnedLeave;
    }
}
