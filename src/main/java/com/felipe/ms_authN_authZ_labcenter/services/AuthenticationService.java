package com.felipe.ms_authN_authZ_labcenter.services;

import com.felipe.ms_authN_authZ_labcenter.dtos.LoginRequestDTO;
import com.felipe.ms_authN_authZ_labcenter.dtos.LoginResponseDTO;
import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenService jwtTokenService;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                    JWTTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO requestDto) {
        var usernameToken = new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password());
        Authentication authentication = authenticationManager.authenticate(usernameToken);

        final String tokenJwt = jwtTokenService.generateToken((User) authentication.getPrincipal());
        final UserRole userRole = ((User) authentication.getPrincipal()).getRole();

        return new LoginResponseDTO(tokenJwt, userRole);
    }
}
