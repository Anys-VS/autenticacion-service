package com.ecommerce.autenticacion_service.controller;

import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Recibe peticiones
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
