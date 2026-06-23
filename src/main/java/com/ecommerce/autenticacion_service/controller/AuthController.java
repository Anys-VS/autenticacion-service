package com.ecommerce.autenticacion_service.controller;

import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/auth")
@Tag(
    name = "Autenticación",
    description = "Endpoints para autenticación y generación de JWT"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
        summary = "Iniciar sesión",
        description = "Valida las credenciales y retorna un JWT"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Login exitoso"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Credenciales inválidas"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {

        AuthResponse response = authService.login(request);

        response.add(
                linkTo(AuthController.class)
                        .withRel("auth")
        );

        return ResponseEntity.ok(response);
    }
}