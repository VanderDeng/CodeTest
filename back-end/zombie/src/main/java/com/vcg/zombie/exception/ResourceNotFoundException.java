package com.su.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Accounting Service ResourceNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ServiceException {
    /**
     * ResourceNotFoundException.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.NOT_FOUND.value());
        this.setErrorCode("USER_INFO_NOT_FOUND");
        this.setErrorType(ErrorType.Client);
    }
}
