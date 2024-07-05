package com.felipe.ms_authN_authZ_labcenter.security;

import com.felipe.ms_authN_authZ_labcenter.repositories.UserRepository;
import com.felipe.ms_authN_authZ_labcenter.services.JWTTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JWTTokenService jwtTokenService;
    private UserRepository userRepository;

    public JWTAuthenticationFilter(JWTTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = getToken(request);

        if (jwtToken != null) {
            String username = jwtTokenService.validateTokenAndGetUsername(jwtToken);
            UserDetails user = userRepository.findByUsername(username);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return authHeader == null
                ? null
                : authHeader.replace("Bearer ", "");
    }
}
