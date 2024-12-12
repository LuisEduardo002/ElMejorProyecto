package com.example.demo.Controller;

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
        String sql = "SELECT * FROM lugar";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getLugarById(@PathVariable int id) {
        String sql = "SELECT * FROM lugar WHERE lugarID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addLugar(@RequestBody Map<String, Object> lugar) {
        String sql = "INSERT INTO lugar (lugarID, nombre, direccion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                lugar.get("lugarID"),
                lugar.get("nombre"),
                lugar.get("direccion")
        );
    }

    @PutMapping("/{id}")
    public void updateLugar(@PathVariable int id, @RequestBody Map<String, Object> lugar) {
        String sql = "UPDATE lugar SET nombre = ?, direccion = ? WHERE lugarID = ?";
        jdbcTemplate.update(sql,
                lugar.get("nombre"),
                lugar.get("direccion"),
                id
        );
    }

    @DeleteMapping("/{id}")
    public void deleteLugar(@PathVariable int id) {
        String sql = "DELETE FROM lugar WHERE lugarID = ?";
        jdbcTemplate.update(sql, id);
    }
}