package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllEventos() {
        String sql = "SELECT * FROM eventos";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getEventoById(@PathVariable int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addEvento(@RequestBody Map<String, Object> evento) {
        String sql = "INSERT INTO eventos (nombre, descripcion, fecha, lugar_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, evento.get("nombre"), evento.get("descripcion"), evento.get("fecha"), evento.get("lugar_id"));
    }

    @PutMapping("/{id}")
    public void updateEvento(@PathVariable int id, @RequestBody Map<String, Object> evento) {
        String sql = "UPDATE eventos SET nombre = ?, descripcion = ?, fecha = ?, lugar_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, evento.get("nombre"), evento.get("descripcion"), evento.get("fecha"), evento.get("lugar_id"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
