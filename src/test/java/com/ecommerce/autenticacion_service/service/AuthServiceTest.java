package com.ecommerce.autenticacion_service.service;

import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.dto.UsuarioClientResponse;
import com.ecommerce.autenticacion_service.exception.CredencialesInvalidasException;
import com.ecommerce.autenticacion_service.util.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private AuthRequest request;
    private UsuarioClientResponse usuario;

    @BeforeEach
    void setUp() {

        request = new AuthRequest();
        request.setEmail("juan@correo.cl");
        request.setPassword("123456");

        usuario = new UsuarioClientResponse();
        usuario.setId(1L);
        usuario.setEmail("juan@correo.cl");
        usuario.setRol("ADMIN");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setContrasena(
                encoder.encode("123456")
        );
    }

    @Test
    void deberiaLoginExitosamente() {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(
                "/usuarios/email/{email}",
                "juan@correo.cl"))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.bodyToMono(UsuarioClientResponse.class))
                .thenReturn(Mono.just(usuario));

        when(jwtUtil.generateToken(
                "juan@correo.cl",
                1L,
                "ADMIN"))
                .thenReturn("jwt-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());

        verify(jwtUtil).generateToken(
                "juan@correo.cl",
                1L,
                "ADMIN");
    }

    @Test
    void deberiaLanzarErrorSiPasswordIncorrecta() {

        request.setPassword("password-mala");

        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(
                "/usuarios/email/{email}",
                "juan@correo.cl"))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.bodyToMono(UsuarioClientResponse.class))
                .thenReturn(Mono.just(usuario));

        CredencialesInvalidasException exception =
                assertThrows(
                        CredencialesInvalidasException.class,
                        () -> authService.login(request)
                );

        assertEquals(
                "Password incorrecta",
                exception.getMessage()
        );
    }

    @Test
    void deberiaLanzarErrorSiUsuarioNoExiste() {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(
                "/usuarios/email/{email}",
                "juan@correo.cl"))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.bodyToMono(UsuarioClientResponse.class))
                .thenReturn(Mono.empty());

        CredencialesInvalidasException exception =
                assertThrows(
                        CredencialesInvalidasException.class,
                        () -> authService.login(request)
                );

        assertEquals(
                "Password incorrecta",
                exception.getMessage()
        );
    }
}