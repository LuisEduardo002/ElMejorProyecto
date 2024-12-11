package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)
    private Evento evento;

    @Column(name = "cantidad_entradas", nullable = false)
    private int cantidadEntradas;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    public enum EstadoReserva {
        ACTIVA, CANCELADA
    }
}
