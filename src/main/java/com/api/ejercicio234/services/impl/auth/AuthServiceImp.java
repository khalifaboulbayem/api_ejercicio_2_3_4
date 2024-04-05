package com.api.ejercicio234.services.impl.auth;

import org.springframework.stereotype.Service;

import com.api.ejercicio234.config.jwt.JwtService;
import com.api.ejercicio234.dto.auth.requests.LoginRequest;
import com.api.ejercicio234.dto.auth.responses.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = (UserDetails) userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("ERROR: " + e.getMessage());
        }

    }

}
