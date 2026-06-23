package com.ecommerce.autenticacion_service.filter;

import com.ecommerce.autenticacion_service.util.JwtUtil;

import jakarta.servlet.FilterChain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void limpiarContexto() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void deberiaAutenticarUsuarioConTokenValido() throws Exception {

        String token = "jwt-token";
        String email = "juan@correo.cl";

        MockHttpServletRequest request =
                new MockHttpServletRequest();

        request.addHeader(
                "Authorization",
                "Bearer " + token
        );

        MockHttpServletResponse response =
                new MockHttpServletResponse();

        when(jwtUtil.extractEmail(token))
                .thenReturn(email);

        when(jwtUtil.isTokenValid(token, email))
                .thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNotNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        assertEquals(
                email,
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void noDebeAutenticarSiNoExisteHeaderAuthorization() throws Exception {

        MockHttpServletRequest request =
                new MockHttpServletRequest();

        MockHttpServletResponse response =
                new MockHttpServletResponse();

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void noDebeAutenticarSiTokenEsInvalido() throws Exception {

        String token = "token-invalido";
        String email = "juan@correo.cl";

        MockHttpServletRequest request =
                new MockHttpServletRequest();

        request.addHeader(
                "Authorization",
                "Bearer " + token
        );

        MockHttpServletResponse response =
                new MockHttpServletResponse();

        when(jwtUtil.extractEmail(token))
                .thenReturn(email);

        when(jwtUtil.isTokenValid(token, email))
                .thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain)
                .doFilter(request, response);
    }
}