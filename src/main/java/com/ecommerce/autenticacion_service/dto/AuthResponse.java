package com.ecommerce.autenticacion_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
//datos de entrada y salida
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
