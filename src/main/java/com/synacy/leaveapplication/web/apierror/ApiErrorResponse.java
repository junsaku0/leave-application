package com.synacy.leaveapplication.web.apierror;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiErrorResponse {
  @Getter
  public String message;
        public ApiErrorResponse(String message, String s) {
            super();
            this.message = message;
        }
    }


