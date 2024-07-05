package com.felipe.ms_authN_authZ_labcenter.controllers;

import com.felipe.ms_authN_authZ_labcenter.dtos.LoginRequestDTO;
import com.felipe.ms_authN_authZ_labcenter.dtos.LoginResponseDTO;
import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.entities.UserRole;
import com.felipe.ms_authN_authZ_labcenter.repositories.UserRepository;
import com.felipe.ms_authN_authZ_labcenter.services.JWTTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenService jwtTokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        var usernameToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication authentication = authenticationManager.authenticate(usernameToken);

        final String tokenJwt = jwtTokenService.generateToken((User) authentication.getPrincipal());
        final UserRole userRole = ((User) authentication.getPrincipal()).getRole();

        LoginResponseDTO responseBody = new LoginResponseDTO(tokenJwt, userRole);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
