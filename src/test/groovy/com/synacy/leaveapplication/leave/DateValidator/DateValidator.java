package com.synacy.leaveapplication.leave.DateValidator;

import java.time.LocalDate;

public class DateValidator {
    boolean invalidEndDate(LocalDate startDate, LocalDate endDate) {
        return endDate.isBefore(startDate);
    }
}
