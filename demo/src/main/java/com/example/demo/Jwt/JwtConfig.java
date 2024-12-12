package com.example.demo.Jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Configuration
public class JwtConfig {

    @Bean
    public Key jwtSecretKey() {
        String secret = "ClaveSuperSecretaDeAlMenos256BitsQueSeaFija!";
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // Usamos la clave fija para generar el Key
    }
}
