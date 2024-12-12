package com.example.demo.Repository;

import com.example.demo.Entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReservaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM reserva";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM reserva WHERE reservaID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public void save(Map<String, Object> reserva) {
        String sql = "INSERT INTO reserva (reservaID, usuarioID, eventoID, fechaReserva, cantidadEntradas, total) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reserva.get("reservaID"),
                reserva.get("usuarioID"),
                reserva.get("eventoID"),
                reserva.get("fechaReserva"),
                reserva.get("cantidadEntradas"),
                reserva.get("total"));
    }

    public void update(int id, Map<String, Object> reserva) {
        String sql = "UPDATE reserva SET usuarioID = ?, eventoID = ?, fechaReserva = ?, cantidadEntradas = ?, total = ? WHERE reservaID = ?";
        jdbcTemplate.update(sql,
                reserva.get("usuarioID"),
                reserva.get("eventoID"),
                reserva.get("fechaReserva"),
                reserva.get("cantidadEntradas"),
                reserva.get("total"), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM reserva WHERE reservaID = ?";
        jdbcTemplate.update(sql, id);
    }
}