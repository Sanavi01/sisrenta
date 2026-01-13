package com.jirehcompanyit.sisrenta.domain.model;

import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.NuevoCelularEsIgualAlAnteriorException;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad de dominio que representa un Cliente dentro del sistema.
 *
 * <p>Un cliente puede tener alquileres y mantiene un estado
 * que indica si se encuentra activo o inactivo.</p>
 *
 * <p>Reglas de negocio:
 * <ul>
 *   <li>Un número de celular es único y no puede estar asociado a más de un cliente.</li>
 *   <li>Un cliente puede ser activado o desactivado, pero no eliminado del sistema.</li>
 * </ul>
 *
 * <p>La entidad es responsable de mantener la consistencia de su propio estado
 * mediante métodos de dominio.</p>
 *
 * @author Santiago Angarita Avila
 */


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

    public void actualizarCelular(String nuevoCelular) {
        if (this.celular.equals(nuevoCelular)) {
            throw new NuevoCelularEsIgualAlAnteriorException("El nuevo celular que esta intentando registrar es igual al que ya esta en la base de datos");
        }
        this.celular = nuevoCelular;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }
}


