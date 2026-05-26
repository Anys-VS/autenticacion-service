package com.ecommerce.autenticacion_service.service;
import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.dto.UsuarioClientResponse;
import com.ecommerce.autenticacion_service.exception.CredencialesInvalidasException;
import com.ecommerce.autenticacion_service.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final WebClient webClient;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(WebClient webClient, JwtUtil jwtUtil) {
        this.webClient = webClient;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public AuthResponse login(AuthRequest request) {
        logger.info("Intento de login para email: {}", request.getEmail());
        UsuarioClientResponse usuario;
        try {
            usuario = webClient.get()
                    .uri("/usuarios/email/{email}", request.getEmail())
                    .retrieve()
                    .bodyToMono(UsuarioClientResponse.class)
                    .block();

        } catch (WebClientResponseException e) {
            logger.warn("Usuario no encontrado para email: {}", request.getEmail());
            throw new CredencialesInvalidasException("Usuario incorrecto");
        }
        if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getContrasena())) {
            logger.warn("Contraseña incorrecta para email: {}", request.getEmail());
            throw new CredencialesInvalidasException("Password incorrecta");
        }
        String rol = usuario.getRol() != null ? usuario.getRol() : "USUARIO";
        String token = jwtUtil.generateToken(request.getEmail(), usuario.getId(), rol);
        logger.info("Login exitoso para email: {} con rol {}", request.getEmail(), rol);
        return new AuthResponse(token);
    }
}