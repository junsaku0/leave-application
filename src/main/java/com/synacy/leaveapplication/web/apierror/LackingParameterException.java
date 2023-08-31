package com.synacy.leaveapplication.web.apierror;

public class LackingParameterException extends RuntimeException {
    public LackingParameterException(String errorMessage) {
        super(errorMessage);
    }
}
