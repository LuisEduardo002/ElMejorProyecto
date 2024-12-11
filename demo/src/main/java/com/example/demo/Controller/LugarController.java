package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lugares")
public class LugarController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllLugares() {
        String sql = "SELECT * FROM lugares";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getLugarById(@PathVariable int id) {
        String sql = "SELECT * FROM lugares WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addLugar(@RequestBody Map<String, Object> lugar) {
        String sql = "INSERT INTO lugares (nombre, direccion, ciudad) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, lugar.get("nombre"), lugar.get("direccion"), lugar.get("ciudad"));
    }

    @PutMapping("/{id}")
    public void updateLugar(@PathVariable int id, @RequestBody Map<String, Object> lugar) {
        String sql = "UPDATE lugares SET nombre = ?, direccion = ?, ciudad = ? WHERE id = ?";
        jdbcTemplate.update(sql, lugar.get("nombre"), lugar.get("direccion"), lugar.get("ciudad"), id);
    }

    @DeleteMapping("/{id}")
    public void deleteLugar(@PathVariable int id) {
        String sql = "DELETE FROM lugares WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}