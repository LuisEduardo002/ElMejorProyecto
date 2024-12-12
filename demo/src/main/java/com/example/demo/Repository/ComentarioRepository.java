package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComentarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM comentario";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM comentario WHERE comentarioID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public void save(Map<String, Object> comentario) {
        String sql = "INSERT INTO comentario (comentarioID, usuarioID, eventoID, calificacion, texto, fechaComentario) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comentario.get("comentarioID"),
                comentario.get("usuarioID"),
                comentario.get("eventoID"),
                comentario.get("calificacion"),
                comentario.get("texto"),
                comentario.get("fechaComentario"));
    }

    public void update(int id, Map<String, Object> comentario) {
        String sql = "UPDATE comentario SET usuarioID = ?, eventoID = ?, calificacion = ?, texto = ?, fechaComentario = ? WHERE comentarioID = ?";
        jdbcTemplate.update(sql,
                comentario.get("usuarioID"),
                comentario.get("eventoID"),
                comentario.get("calificacion"),
                comentario.get("texto"),
                comentario.get("fechaComentario"), id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM comentario WHERE comentarioID = ?";
        jdbcTemplate.update(sql, id);
    }
}