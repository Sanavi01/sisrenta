package com.jirehcompanyit.sisrenta.domain.model;

import com.jirehcompanyit.sisrenta.domain.enums.EstadoAlquiler;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "alquileres")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relaciones
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    // Datos del Alquiler
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaCancelacion;

    private LocalDateTime fechaEntrega;

    private LocalDateTime fechaLimiteDevolucion;

    private LocalDateTime fechaDevolucion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlquiler estadoAlquiler;

    @OneToMany(
            mappedBy = "alquiler",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemAlquiler> itemsAlquiler = new ArrayList<>();

    //-- Constructor

    @Builder
    public Alquiler(Cliente cliente, Empleado empleado) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaCreacion = LocalDateTime.now();
        this.estadoAlquiler = EstadoAlquiler.CREADO;
    }

    // -- Comportamiento del Dominio

    public void marcarComoCancelado() {
        if (this.estadoAlquiler != EstadoAlquiler.CREADO) {
            throw new IllegalStateException("Solo se puede cancelar un alquiler que haya sido creado");
        }
        this.estadoAlquiler = EstadoAlquiler.CANCELADO;
        this.fechaCancelacion = LocalDateTime.now();
    }

    public void marcarComoEntregado() {
        if (this.estadoAlquiler != EstadoAlquiler.CREADO) {
            throw new IllegalStateException("Solo se puede entregar un alquiler creado");
        }
        this.estadoAlquiler = EstadoAlquiler.ENTREGADO;
        this.fechaEntrega = LocalDateTime.now();
        this.fechaLimiteDevolucion = this.fechaEntrega.plusDays(3);
    }

    public void marcarComoDevuelto() {
        if (this.estadoAlquiler != EstadoAlquiler.ENTREGADO
                && this.estadoAlquiler != EstadoAlquiler.ATRASADO) {
            throw new IllegalStateException("Solo se puede devolver un alquiler entregado o atrasado");
        }
        this.estadoAlquiler = EstadoAlquiler.DEVUELTO;
        this.fechaDevolucion = LocalDateTime.now();
    }

    public void estaAtrasado() {
        if (this.estadoAlquiler == EstadoAlquiler.ENTREGADO && LocalDateTime.now().isAfter(this.fechaLimiteDevolucion)) {
            this.estadoAlquiler = EstadoAlquiler.ATRASADO;
        }
    }

    public ItemAlquiler agregarItem(String nombreTraje, String descripcion, int cantidad, int precioUnitario) {
        if (this.estadoAlquiler != EstadoAlquiler.CREADO) {
            throw new IllegalStateException("No es posible agregar un item a un alquiler no creado");
        }
        ItemAlquiler itemAlquiler = ItemAlquiler.builder()
                .alquiler(this)
                .nombreTraje(nombreTraje)
                .descripcion(descripcion)
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .build();

        this.itemsAlquiler.add(itemAlquiler);
        return itemAlquiler;
    }

    public int calcularTotal() {
        return itemsAlquiler.stream()
                .mapToInt(ItemAlquiler::subtotal)
                .sum();
    }

    public List<ItemAlquiler> getItemsAlquiler() {
        return Collections.unmodifiableList(itemsAlquiler);
    }
}
