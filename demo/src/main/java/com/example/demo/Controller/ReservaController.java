package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllReservas() {
        String sql = "SELECT * FROM reservas";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getReservaById(@PathVariable int id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addReserva(@RequestBody Map<String, Object> reserva) {
        String sql = "INSERT INTO reservas (usuario_id, evento_id, fecha_reserva) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reserva.get("usuario_id"), reserva.get("evento_id"), reserva.get("fecha_reserva"));
    }

    @PutMapping("/{id}")
    public void updateReserva(@PathVariable int id, @RequestBody Map<String, Object> reserva) {
        String sql = "UPDATE reservas SET usuario_id = ?, evento_id = ?, fecha_reserva = ? WHERE id = ?";
        jdbcTemplate.update(sql, reserva.get("usuario_id"), reserva.get("evento_id"), reserva.get("fecha_reserva"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable int id) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
