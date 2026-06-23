package com.ecommerce.autenticacion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación")
public class AuthResponse extends RepresentationModel<AuthResponse> {

    @Schema(description = "JWT generado")
    private String token;
}