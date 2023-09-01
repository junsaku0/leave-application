package com.synacy.leaveapplication.web.apierror;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceededLeaveBalanceException.class)
    public ResponseEntity<String> handleExceededLeaveBalanceException(ExceededLeaveBalanceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ApiErrorResponse handleResourceNotFoundException() {
        return new ApiErrorResponse("RESOURCE_NOT_FOUND", "The target resource does not exist");
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidOperationException.class)
    public ApiErrorResponse handleInvalidOperationException(InvalidOperationException e) {
        return new ApiErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LackingParameterException.class)
    public ApiErrorResponse handleLackingParameterException(LackingParameterException e) {
        return new ApiErrorResponse("LACKING_PARAMETER", e.getMessage());
    }
}