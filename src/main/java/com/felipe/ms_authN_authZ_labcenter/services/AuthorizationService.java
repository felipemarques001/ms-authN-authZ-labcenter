package com.felipe.ms_authN_authZ_labcenter.services;

import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public Boolean verifyUserAuthenticatedIsAdmin() {
        User userAuthenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAuthenticated.getRole().equals(UserRole.ADMIN);
    }

    public Boolean verifyUserAuthenticatedIsUser() {
        User userAuthenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userAuthenticated.getRole().equals(UserRole.USER);
    }
}
