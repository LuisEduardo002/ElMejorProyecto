package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Double getSaldo(Long id) {
        String sql = "SELECT saldo FROM usuario WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Double.class);
    }

    public void addSaldo(Long id, Double monto) {
        String sql = "UPDATE usuario SET saldo = saldo + ? WHERE id = ?";
        jdbcTemplate.update(sql, monto, id);
    }
}
