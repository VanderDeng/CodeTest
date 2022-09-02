package com.vcg.zombie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid parameter exception.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends ServiceException {
    /**
     * Invalid parameter exception content.
     *
     * @param message Invalid parameter
     */
    public InvalidParameterException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorCode("INVALID_PARAMETER");
        this.setErrorType(ErrorType.Client);
    }
}
