package com.unyx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalideGrantTypeException extends RuntimeException{
  public  InvalideGrantTypeException(){super("Invalid Grant Type. Please provide correct grant type");}
}
