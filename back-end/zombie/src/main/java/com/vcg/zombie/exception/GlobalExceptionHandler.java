package com.vcg.zombie.exception;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * handle all exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<?> handleResourceNotFoundException(ServiceException ex) {
        val errorResponse = ErrorResponse.builder()
                                    .statusCode(ex.getStatusCode())
                                    .code(ex.getErrorCode())
                                    .errorType(ex.getErrorType())
                                    .message((ex.getMessage()))
                                    .build();
        return ResponseEntity
                       .status(ex.getStatusCode() != 0 ? ex.getStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR.value())
                       .body(errorResponse);
    }
}
