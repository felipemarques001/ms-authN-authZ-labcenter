package com.felipe.ms_authN_authZ_labcenter.entities;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
