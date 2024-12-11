package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllComentarios() {
        String sql = "SELECT * FROM comentarios";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getComentarioById(@PathVariable int id) {
        String sql = "SELECT * FROM comentarios WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addComentario(@RequestBody Map<String, Object> comentario) {
        String sql = "INSERT INTO comentarios (contenido, usuario_id, fecha) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, comentario.get("contenido"), comentario.get("usuario_id"), comentario.get("fecha"));
    }

    @PutMapping("/{id}")
    public void updateComentario(@PathVariable int id, @RequestBody Map<String, Object> comentario) {
        String sql = "UPDATE comentarios SET contenido = ?, usuario_id = ?, fecha = ? WHERE id = ?";
        jdbcTemplate.update(sql, comentario.get("contenido"), comentario.get("usuario_id"), comentario.get("fecha"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable int id) {
        String sql = "DELETE FROM comentarios WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
