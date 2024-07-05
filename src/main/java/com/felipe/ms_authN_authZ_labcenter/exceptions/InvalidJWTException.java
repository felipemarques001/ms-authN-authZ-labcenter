package com.felipe.ms_authN_authZ_labcenter.exceptions;

public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException() {
        super("The JWT token sent is invalid!");
    }
}
