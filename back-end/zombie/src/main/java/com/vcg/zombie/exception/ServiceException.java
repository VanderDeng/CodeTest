package com.vcg.zombie.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Accounting Service Exception.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode;
    private ErrorType errorType; // Service, Client, Unknown

    /**
     * the error type enum.
     */
    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
