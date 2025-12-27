package com.jirehcompanyit.sisrenta.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items_alquiler")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemAlquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "alquiler_id")
    private Alquiler alquiler;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int precioUnitario;

    @Builder
    public ItemAlquiler(Alquiler alquiler, String descripcion, int cantidad, int precioUnitario){
        this.alquiler = alquiler;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int subtotal() {
        return cantidad * precioUnitario;
    }





}
