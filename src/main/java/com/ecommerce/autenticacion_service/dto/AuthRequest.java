package com.ecommerce.autenticacion_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
//usamos dto para separar los datos de entrada, para no exponer directamente la entidad usuario
//controla que recibe la Api


//aqui no necesito todo para login solo basta esto:
@Data
@Schema(description = "Credenciales para autenticación")
public class AuthRequest {

    @Schema(example = "juan@correo.cl")
    private String email;

    @Schema(example = "123456")
    private String password;
}
//es un dto especifico, controlamos lo que entra

//  Controller
//     ↓
//  DTO (AuthRequest)
//     ↓
//  Service
//     ↓
//  Repository
//     ↓
//  Oracle DB