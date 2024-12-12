package com.example.demo.Controller;

import com.example.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Double> getSaldo(@PathVariable Long id) {
        Double saldo = usuarioService.getSaldo(id);
        return ResponseEntity.ok(saldo);
    }

    @PostMapping("/{id}/saldo")
    public ResponseEntity<Void> addSaldo(@PathVariable Long id, @RequestBody Double monto) {
        usuarioService.addSaldo(id, monto);
        return ResponseEntity.ok().build();
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Map<String, Object>> usuarios = jdbcTemplate.queryForList(sql);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable Integer id) {
        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            Map<String, Object> usuario = jdbcTemplate.queryForMap(sql, id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Agregar un nuevo usuario
    @PostMapping
    public ResponseEntity<Void> addUsuario(@RequestBody Map<String, Object> usuario) {
        try {
            String sql = "INSERT INTO usuario (username, password, first_name, last_name, email, saldo, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    usuario.get("username"),
                    usuario.get("password"),
                    usuario.get("first_name"), // Ajuste aquí
                    usuario.get("last_name"),  // Ajuste aquí
                    usuario.get("email"),
                    usuario.get("saldo"),
                    usuario.get("tipo_usuario"));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable Integer id, @RequestBody Map<String, Object> usuarioActualizado) {
        try {
            String sql = "UPDATE usuario SET username = ?, first_name = ?, last_name = ?, email = ?, saldo = ?, tipo_usuario = ? WHERE id = ?";
            int rowsAffected = jdbcTemplate.update(sql,
                    usuarioActualizado.get("username"),

                    usuarioActualizado.get("first_name"),
                    usuarioActualizado.get("last_name"),
                    usuarioActualizado.get("email"),
                    usuarioActualizado.get("saldo"),
                    usuarioActualizado.get("tipo_usuario"),
                    id);

            if (rowsAffected > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
