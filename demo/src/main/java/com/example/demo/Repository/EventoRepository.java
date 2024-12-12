package com.example.demo.Repository;

import com.example.demo.Entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EventoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM evento";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM evento WHERE eventoid = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public void save(Map<String, Object> evento) {

        String sql = "INSERT INTO evento (eventoid, organizadorid, nombre, descripcion, fecha, hora, precio, capacidad, lugarid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                evento.get("eventoid"),
                evento.get("organizadorid"),
                evento.get("nombre"),
                evento.get("descripcion"),
                evento.get("fecha"),
                evento.get("hora"),
                evento.get("precio"),
                evento.get("capacidad"),
                evento.get("lugarid"));
    }

    public void update(int id, Map<String, Object> evento) {
        String sql = "UPDATE evento SET organizadorid = ?, nombre = ?, descripcion = ?, fecha = ?, hora = ?, precio = ?, capacidad = ?, lugarid = ? WHERE eventoID = ?";
        jdbcTemplate.update(sql,
                evento.get("organizadorid"),
                evento.get("nombre"),
                evento.get("descripcion"),
                evento.get("fecha"),
                evento.get("hora"),
                evento.get("precio"),
                evento.get("capacidad"),
                evento.get("lugarid"), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM evento WHERE eventoid = ?";
        jdbcTemplate.update(sql, id);
    }
}
