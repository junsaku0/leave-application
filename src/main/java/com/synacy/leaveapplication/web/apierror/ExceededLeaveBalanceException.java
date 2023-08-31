package com.synacy.leaveapplication.web.apierror;

public class ExceededLeaveBalanceException extends RuntimeException{
    public ExceededLeaveBalanceException(String message){
        super(message);
    }
}
