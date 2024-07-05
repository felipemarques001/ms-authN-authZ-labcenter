package com.felipe.ms_authN_authZ_labcenter.controllers;

import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;
import com.felipe.ms_authN_authZ_labcenter.services.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authorization")
public class AuthorizationController {

    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("admin")
    public ResponseEntity<Void> authorizeAdmin() {
        final Boolean userHasAdminRole = service.verifyUserAuthenticatedIsAdmin();
        return userHasAdminRole
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("user")
    public ResponseEntity<Void> authorizeUser() {
        final Boolean userHasAdminRole = service.verifyUserAuthenticatedIsUser();
        return userHasAdminRole
                ? ResponseEntity.status(HttpStatus.OK).build()
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
