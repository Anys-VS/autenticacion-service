package com.ecommerce.autenticacion_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailYaRegistradoException extends RuntimeException {
    public EmailYaRegistradoException(String mensaje) {
        super(mensaje);
    }
}