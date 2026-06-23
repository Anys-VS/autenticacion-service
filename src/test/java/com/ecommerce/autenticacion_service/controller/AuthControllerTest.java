package com.ecommerce.autenticacion_service.controller;

import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.exception.CredencialesInvalidasException;
import com.ecommerce.autenticacion_service.service.AuthService;
import com.ecommerce.autenticacion_service.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("POST /auth/login debe retornar 200 y JWT")
    void loginExitoso() throws Exception {

        AuthRequest request = new AuthRequest();
        request.setEmail("juan@correo.cl");
        request.setPassword("123456");

        AuthResponse response = new AuthResponse("jwt-token");

        when(authService.login(any(AuthRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    @DisplayName("POST /auth/login debe retornar 401 cuando credenciales son inválidas")
    void loginCredencialesInvalidas() throws Exception {

        AuthRequest request = new AuthRequest();
        request.setEmail("juan@correo.cl");
        request.setPassword("incorrecta");

        when(authService.login(any(AuthRequest.class)))
                .thenThrow(new CredencialesInvalidasException("Password incorrecta"));

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}