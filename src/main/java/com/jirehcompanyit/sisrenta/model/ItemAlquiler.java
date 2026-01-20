package com.jirehcompanyit.sisrenta.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entidad de dominio que representa un Item de Alquiler dentro del sistema.
 *
 * <p>Un item esta asociado a un alquiler, como contenedor de items,
 * es el que da informacion detallada de las condiciones y caracteristicas de las
 * prendas que se llevan en el alquiler.</p>
 *
 * <p>Reglas de negocio:
 * <ul>
 *   <li>Un item tiene que estar asociada a un alquiler</li>
 *   <li>La cantidad del item tiene que ser mayor a 0.</li>
 *   <li>El precio del item tiene que ser mayor a 0 .</li>
 * </ul>
 *
 * @author Santiago Angarita Avila
 */

@Entity
@Table(name = "items_alquiler")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemAlquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "alquiler_id")
    private Alquiler alquiler;

    @Column(nullable = false)
    private String nombreTraje;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private int precioUnitario;

    @Builder
    public ItemAlquiler(Alquiler alquiler, String nombreTraje, String descripcion, int cantidad, int precioUnitario) {
        this.alquiler = alquiler;
        this.nombreTraje = nombreTraje;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        if (precioUnitario <= 0) {
            throw new IllegalArgumentException("La precio debe ser mayor a 0");
        }
    }

    public int subtotal() {
        return cantidad * precioUnitario;
    }
}
