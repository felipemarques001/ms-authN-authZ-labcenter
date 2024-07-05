package com.felipe.ms_authN_authZ_labcenter.controllers;

import com.felipe.ms_authN_authZ_labcenter.dtos.LoginRequestDTO;
import com.felipe.ms_authN_authZ_labcenter.dtos.LoginResponseDTO;
import com.felipe.ms_authN_authZ_labcenter.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService authenticationService){
        service = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        LoginResponseDTO responseBody = service.loginUser(data);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<Void> authenticateUser() {
        // A pilha de execução do código só vai
        // chegar aqui se o usuário conseguir se autenticar com o token enviado
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
