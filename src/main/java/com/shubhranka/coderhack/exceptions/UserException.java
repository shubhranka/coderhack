package com.shubhranka.coderhack.exceptions;

import org.springframework.http.HttpStatusCode;

public class UserException extends RuntimeException {

    private final HttpStatusCode statusCode;
    public UserException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

}
