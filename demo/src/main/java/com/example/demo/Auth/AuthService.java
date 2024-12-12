package com.example.demo.Auth;

import com.example.demo.Jwt.JwtService;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
                return AuthResponse.builder()
                        .token(token)
                        .build();
    }

    public AuthResponse register(RegisterRequest request) {
        try {
            Usuario user = Usuario.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .first_name(request.getFirstName())
                    .last_name(request.getLastName())
                    .email(request.getEmail())
                    .saldo(request.getSaldo() != null ? request.getSaldo() : 0.0)
                    .tipoUsuario(Role.valueOf(request.getTipo_usuario())) // Use getTipo_usuario()
                    .build();

            userRepository.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid user role: " + request.getTipo_usuario(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        }
    }

}
