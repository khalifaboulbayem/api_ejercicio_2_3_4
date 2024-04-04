package com.api.ejercicio234.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ejercicio234.dto.request.UserDto;
import com.api.ejercicio234.models.User;
import com.api.ejercicio234.services.impl.UserServiceImp;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @PostMapping("/login")
    // public ResponseEntity<User> login(@RequestBody LoginRequest request) {
    // return ResponseEntity.ok(authService.login(request));
    // }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        User user = User.builder()
                .id(request.getId())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
        userService.create(user);
        return ResponseEntity.ok(user);
    }
}
