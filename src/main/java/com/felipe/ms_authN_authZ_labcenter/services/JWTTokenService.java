package com.felipe.ms_authN_authZ_labcenter.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipe.ms_authN_authZ_labcenter.entities.User;
import com.felipe.ms_authN_authZ_labcenter.exceptions.JwtTokenCreationException;
import com.felipe.ms_authN_authZ_labcenter.exceptions.JwtTokenInvalidException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTTokenService {

    private final String SECRET = "my-secret";
    private final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    private final String ISSUER = "ms-authN-authZ-labcenter";

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(ALGORITHM);
        } catch (JWTCreationException exception) {
            throw new JwtTokenCreationException();
        }
    }

    public String validateTokenAndGetUsername(String token) {
        try {
            return JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JwtTokenInvalidException();
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.ofHours(-3));
    }
}
