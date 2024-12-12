package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String sql = "SELECT * FROM evento";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getEventoById(@PathVariable int id) {
        String sql = "SELECT * FROM evento WHERE eventoID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public ResponseEntity<String> addEvento(@RequestBody Map<String, Object> evento) {
        try {
            String sql = "INSERT INTO evento (eventoID, organizadorID, nombre, descripcion, fecha, hora, precio, capacidad, lugarID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    evento.get("eventoID"),
                    evento.get("organizadorID"),
                    evento.get("nombre"),
                    evento.get("descripcion"),
                    evento.get("fecha"),
                    evento.get("hora"),
                    evento.get("precio"),
                    evento.get("capacidad"),
                    evento.get("lugarID")
            );
            return ResponseEntity.ok("Event added successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateEvento(@PathVariable int id, @RequestBody Map<String, Object> evento) {
        String sql = "UPDATE evento SET organizadorID = ?, nombre = ?, descripcion = ?, fecha = ?, hora = ?, precio = ?, capacidad = ?, lugarID = ? WHERE eventoID = ?";
        jdbcTemplate.update(sql,
                evento.get("organizadorID"),
                evento.get("nombre"),
                evento.get("descripcion"),
                evento.get("fecha"),
                evento.get("hora"),
                evento.get("precio"),
                evento.get("capacidad"),
                evento.get("lugarID"),
                id
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable int id) {
        String sql = "DELETE FROM evento WHERE eventoID = ?";
        jdbcTemplate.update(sql, id);
    }
}
