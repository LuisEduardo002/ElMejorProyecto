package com.example.demo.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private Double saldo;
  private String tipo_usuario; // Note the underscore, matching the JSON

  // Getters and setters
  public String getTipo_usuario() {
    return tipo_usuario;
  }

  public void setTipo_usuario(String tipo_usuario) {
    this.tipo_usuario = tipo_usuario;
  }
}