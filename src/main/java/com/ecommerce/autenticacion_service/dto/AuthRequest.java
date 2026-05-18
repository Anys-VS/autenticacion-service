package com.ecommerce.autenticacion_service.dto;

import lombok.Data;
//usamos dto para separar los datos de entrada, para no exponer directamente la entidad usuario
//controla que recibe la Api

//aqui no necesito todo para login solo basta esto:
@Data
public class AuthRequest {
    private String name;
    private String email;
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