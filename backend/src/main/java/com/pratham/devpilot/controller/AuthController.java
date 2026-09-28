package com.pratham.devpilot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pratham.devpilot.dto.request.LoginRequest;
import com.pratham.devpilot.dto.request.RegisterRequest;
import com.pratham.devpilot.dto.response.RegisterResponse;
import com.pratham.devpilot.service.AuthService;
import com.pratham.devpilot.dto.response.LoginResponse;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }
    @PostMapping("/login")
public ResponseEntity<LoginResponse> login(
        @Valid @RequestBody LoginRequest request) {

    return ResponseEntity.ok(
            authService.login(request)
    );
}
}