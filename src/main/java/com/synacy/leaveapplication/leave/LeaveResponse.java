package com.synacy.leaveapplication.leave;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class LeaveResponse {

    @Getter
    Long id;

    @Getter
    String name;

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
        this.id = leave.getId();
        this.name = leave.getName();
        this.fileDate = leave.getFileDate();
        this.startDate = leave.getStartDate();
        this.endDate = leave.getEndDate();
        this.duration = leave.getDuration();
        this.reason = leave.getReason();
        this.status = leave.getStatus();
    }
}
