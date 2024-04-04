package com.api.ejercicio234.config.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.ejercicio234.config.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        com.api.ejercicio234.models.User userEntity = null;
        String username = "";
        String password = "";
        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(), com.api.ejercicio234.models.User.class);
            username = userEntity.getNick();
            password = userEntity.getPassword();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateToken(user.getUsername());

        response.addHeader("Authorization", token);
        Map<String, Object> headers = new HashMap<>();
        headers.put("token", token);
        headers.put("Message", "Autenticacion correcta!");
        headers.put("Username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(headers));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
