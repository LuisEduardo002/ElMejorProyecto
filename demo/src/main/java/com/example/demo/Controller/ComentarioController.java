package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getAllComentarios() {
        String sql = "SELECT * FROM comentario";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getComentarioById(@PathVariable int id) {
        String sql = "SELECT * FROM comentario WHERE comentarioID = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PostMapping
    public void addComentario(@RequestBody Map<String, Object> comentario) {
        String sql = "INSERT INTO comentario (comentarioID, usuarioID, eventoID, calificacion, texto, fechaComentario) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                comentario.get("comentarioID"),
                comentario.get("usuarioID"),
                comentario.get("eventoID"),
                comentario.get("calificacion"),
                comentario.get("texto"),
                comentario.get("fechaComentario")
        );
    }

    @PutMapping("/{id}")
    public void updateComentario(@PathVariable int id, @RequestBody Map<String, Object> comentario) {
        String sql = "UPDATE comentario SET usuarioID = ?, eventoID = ?, calificacion = ?, texto = ?, fechaComentario = ? WHERE comentarioID = ?";
        jdbcTemplate.update(sql,
                comentario.get("usuarioID"),
                comentario.get("eventoID"),
                comentario.get("calificacion"),
                comentario.get("texto"),
                comentario.get("fechaComentario"),
                id
        );
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable int id) {
        String sql = "DELETE FROM comentario WHERE comentarioID = ?";
        jdbcTemplate.update(sql, id);
    }
}
