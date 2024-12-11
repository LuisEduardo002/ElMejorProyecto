package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllUsuarios() {
        String sql = "SELECT * FROM usuarios";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUsuarioById(@PathVariable int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addUsuario(@RequestBody Map<String, Object> usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, usuario.get("nombre"), usuario.get("email"), usuario.get("password"));
    }

    @PutMapping("/{id}")
    public void updateUsuario(@PathVariable int id, @RequestBody Map<String, Object> usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.get("nombre"), usuario.get("email"), usuario.get("password"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
