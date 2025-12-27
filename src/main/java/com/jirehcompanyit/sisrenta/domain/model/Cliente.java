package com.jirehcompanyit.sisrenta.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"celular"})
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private boolean activo;

    @Builder
    public Cliente(String nombre, String apellido, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }
}


