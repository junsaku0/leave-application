package com.synacy.leaveapplication.web.apierror;

public class InsufficientEarnedLeave extends RuntimeException {
    public InsufficientEarnedLeave(String message) {
        super(message);


    }
}