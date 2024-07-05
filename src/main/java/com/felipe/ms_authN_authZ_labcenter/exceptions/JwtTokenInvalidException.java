package com.felipe.ms_authN_authZ_labcenter.exceptions;

public class JwtTokenInvalidException extends RuntimeException {
    public JwtTokenInvalidException() {
        super("The JWT token is invalid!");
    }
}
