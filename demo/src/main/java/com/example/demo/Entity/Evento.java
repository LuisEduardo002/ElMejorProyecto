package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "precio_entrada", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioEntrada;

    @Column(name = "capacidad_total", nullable = false)
    private int capacidadTotal;

    @Column(name = "entradas_disponibles", nullable = false)
    private int entradasDisponibles;

    @ManyToOne
    @JoinColumn(name = "organizador_id", referencedColumnName = "id", nullable = false)
    private User organizador;
}
