package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllCategorias() {
        String sql = "SELECT * FROM categorias";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCategoriaById(@PathVariable int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addCategoria(@RequestBody Map<String, Object> categoria) {
        String sql = "INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, categoria.get("nombre"), categoria.get("descripcion"));
    }

    @PutMapping("/{id}")
    public void updateCategoria(@PathVariable int id, @RequestBody Map<String, Object> categoria) {
        String sql = "UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.get("nombre"), categoria.get("descripcion"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}