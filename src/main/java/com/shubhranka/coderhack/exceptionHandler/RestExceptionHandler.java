package com.shubhranka.coderhack.exceptionHandler;

import com.shubhranka.coderhack.exceptions.UserException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        HttpStatusCode statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof UserException) {
            statusCode = ((UserException) ex).getStatusCode();
        }
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), statusCode, request);
    }
}
