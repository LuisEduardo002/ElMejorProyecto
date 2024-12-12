package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventoID;

    @ManyToOne
    @JoinColumn(name = "organizadorID", nullable = false)
    private Usuario organizador;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "lugarID", nullable = false)
    private Lugar lugar;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(
            name = "Evento_Categoria",
            joinColumns = @JoinColumn(name = "eventoID"),
            inverseJoinColumns = @JoinColumn(name = "categoriaID")
    )
    private List<Categoria> categorias;

    // Getters y setters
}
