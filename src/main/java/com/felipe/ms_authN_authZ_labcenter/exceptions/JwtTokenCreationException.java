package com.felipe.ms_authN_authZ_labcenter.exceptions;

public class JwtTokenCreationException extends RuntimeException {
    public JwtTokenCreationException() {
        super("There was an error at the JWT token creation!");
    }
}
