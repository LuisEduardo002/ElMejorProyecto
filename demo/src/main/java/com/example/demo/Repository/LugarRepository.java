package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LugarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM lugar";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM lugar WHERE lugarID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public void save(Map<String, Object> lugar) {
        String sql = "INSERT INTO lugar (lugarID, nombre, direccion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                lugar.get("lugarID"),
                lugar.get("nombre"),
                lugar.get("direccion"));
    }

    public void update(int id, Map<String, Object> lugar) {
        String sql = "UPDATE lugar SET nombre = ?, direccion = ? WHERE lugarID = ?";
        jdbcTemplate.update(sql,
                lugar.get("nombre"),
                lugar.get("direccion"), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM lugar WHERE lugarID = ?";
        jdbcTemplate.update(sql, id);
    }
}