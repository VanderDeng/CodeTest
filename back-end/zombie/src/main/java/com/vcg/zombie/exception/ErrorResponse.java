package com.su.accounting.exception;

import lombok.Builder;
import lombok.Data;

/**
 * A ErrorResponse.
 */
@Data
@Builder
public class ErrorResponse {
    private String code;
    private ServiceException.ErrorType errorType;
    private String message;
    private int statusCode;
}
