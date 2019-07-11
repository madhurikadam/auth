package com.unyx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthClientException extends RuntimeException {
    public AuthClientException(String exception){super(exception);
    }
}
