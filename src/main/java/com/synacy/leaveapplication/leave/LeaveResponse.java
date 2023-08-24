package com.synacy.leaveapplication.leave;

import lombok.Getter;

import java.time.LocalDate;

public class LeaveResponse {

    @Getter
    LocalDate fileDate;

    @Getter
    LocalDate startDate;

    @Getter
    LocalDate endDate;

    @Getter
    Long duration;

    @Getter
    String reason;

    @Getter
    LeaveStatus status;

    public LeaveResponse(Leave leave) {
        this.fileDate = leave.getFileDate();
        this.startDate = leave.getStartDate();
        this.endDate = leave.getEndDate();
        this.duration = leave.getDuration();
        this.reason = leave.getReason();
        this.status = leave.getStatus();
    }
}
