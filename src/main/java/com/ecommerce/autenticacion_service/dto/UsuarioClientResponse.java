package com.ecommerce.autenticacion_service.dto;

import lombok.Data;

// DTO que representa los datos que recibimos desde el microservicio de usuarios
// cuando consultamos GET /usuarios/email/{email}
@Data
public class UsuarioClientResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String rol;
}
