package com.example.demo.Controller;

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
        String sql = "SELECT * FROM reserva";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getReservaById(@PathVariable int id) {
        String sql = "SELECT * FROM reserva WHERE reservaID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addReserva(@RequestBody Map<String, Object> reserva) {
        String sql = "INSERT INTO reserva (reservaID, usuarioID, eventoID, fechaReserva, cantidadEntradas, total) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reserva.get("reservaID"),
                reserva.get("usuarioID"),
                reserva.get("eventoID"),
                reserva.get("fechaReserva"),
                reserva.get("cantidadEntradas"),
                reserva.get("total")
        );
    }

    @PutMapping("/{id}")
    public void updateReserva(@PathVariable int id, @RequestBody Map<String, Object> reserva) {
        String sql = "UPDATE reserva SET usuarioID = ?, eventoID = ?, fechaReserva = ?, cantidadEntradas = ?, total = ? WHERE reservaID = ?";
        jdbcTemplate.update(sql,
                reserva.get("usuarioID"),
                reserva.get("eventoID"),
                reserva.get("fechaReserva"),
                reserva.get("cantidadEntradas"),
                reserva.get("total"),
                id
        );
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable int id) {
        String sql = "DELETE FROM reserva WHERE reservaID = ?";
        jdbcTemplate.update(sql, id);
    }
}