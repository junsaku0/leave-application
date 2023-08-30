package com.synacy.leaveapplication.web.apierror;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    public int statusCode;
    public String message;

        public ApiErrorResponse(String message, String errorMessage) {
            super();
            this.message = message;
        }
    }


