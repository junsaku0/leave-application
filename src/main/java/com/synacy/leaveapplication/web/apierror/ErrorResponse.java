package com.synacy.leaveapplication.web.apierror;
import java.time.LocalDate;

public class ErrorResponse {
    public ErrorResponse(String status, String message, LocalDate timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    private LocalDate timestamp;


}

