package com.example.demo.Jwt;

import org.apache.catalina.UserDatabase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final Key secretKey;

    public JwtService(Key secretKey) {
        this.secretKey = secretKey;
    }

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {

        return Jwts.builder()

                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 24 minutos
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey) // Configura la clave firmada (aún válida)
                .build()                  // Construye el parser
                .parseClaimsJws(token)    // Analiza el token
                .getBody();               // Obtiene los Claims
    }
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}

