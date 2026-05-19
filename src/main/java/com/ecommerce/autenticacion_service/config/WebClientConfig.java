package com.ecommerce.autenticacion_service.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class WebClientConfig {
    @Value("${usuario.service.url}")
    private String usuarioServiceUrl;
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(usuarioServiceUrl)
                .build();
    }
}