package com.unyx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class JwtCustomException extends RuntimeException {
    public JwtCustomException(String error){super(error);}
}
