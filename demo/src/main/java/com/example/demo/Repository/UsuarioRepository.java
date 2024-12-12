package com.example.demo.Repository;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findById(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public void save(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password, first_name, last_name, email, saldo, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getFirst_name(),
                usuario.getLast_name(),
                usuario.getEmail(),
                usuario.getSaldo(),
                usuario.getTipoUsuario().name()); // Convert enum to its string name
    }

    public void update(int id, Map<String, Object> usuario) {
        String sql = "UPDATE usuario SET username = ?, first_name = ?, last_name = ?, email = ?, saldo = ?, tipo_usuario = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                usuario.get("username"),
                usuario.get("first_name"),
                usuario.get("last_name"),
                usuario.get("email"),
                usuario.get("saldo"),
                usuario.get("tipo_usuario"),
                id);
    }

    public void delete(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Usuario> findByUsername(String username) {
        String sql = "SELECT * FROM usuario WHERE username = ?";
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Usuario usuario = new Usuario();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFirst_name(rs.getString("first_name"));
                usuario.setLast_name(rs.getString("last_name"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSaldo(rs.getDouble("saldo"));

                // Convert string to Role enum
                String tipoUsuarioStr = rs.getString("tipo_usuario");
                usuario.setTipoUsuario(Role.valueOf(tipoUsuarioStr));

                return usuario;
            }, username)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}