package com.ecommerce.autenticacion_service.service;

import com.ecommerce.autenticacion_service.dto.AuthRequest;
import com.ecommerce.autenticacion_service.dto.AuthResponse;
import com.ecommerce.autenticacion_service.dto.RegisterRequest;
import com.ecommerce.autenticacion_service.model.Usuario;
import com.ecommerce.autenticacion_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.autenticacion_service.util.JwtUtil;
import com.ecommerce.autenticacion_service.exception.CredencialesInvalidasException;
import com.ecommerce.autenticacion_service.exception.EmailYaRegistradoException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registrar(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailYaRegistradoException("El email ya está registrado");
        }
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol())
                .build();
        usuarioRepository.save(usuario);
    }

    public AuthResponse login(AuthRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), usuarioOpt.get().getPassword())) {
            throw new CredencialesInvalidasException("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
