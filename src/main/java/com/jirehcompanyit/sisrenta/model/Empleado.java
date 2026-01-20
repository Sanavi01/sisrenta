package com.jirehcompanyit.sisrenta.model;

import com.jirehcompanyit.sisrenta.enums.RolEmpleado;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad de dominio que representa un Empleado dentro del sistema.
 *
 * <p>Un empleado puede tener alquileres y mantiene un estado
 * que indica si se encuentra activo o inactivo, a su vez, tiene un rol, puede ser admin, o, empleado</p>
 *
 * <p>Reglas de negocio:
 * <ul>
 *   <li>Un número de celular es único y no puede estar asociado a más de un empleado.</li>
 *   <li>Un empleado puede ser activado o desactivado, pero no eliminado del sistema.</li>
 * </ul>
 *
 * <p>La entidad es responsable de mantener la consistencia de su propio estado
 * mediante métodos de dominio.</p>
 *
 * @author Santiago Angarita Avila
 */


@Entity
@Table(
        name = "empleados",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "celular")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String celular;
    @Column
    private String direccion;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolEmpleado rol;

    @Column(nullable = false)
    private boolean activo;

    @Builder
    public Empleado(String nombre, String apellido, String celular, String direccion, RolEmpleado rol, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.activo = true;
    }

    public void actualizarEmpleado(String celular, String direccion) {
        if (celular != null) {
            this.celular = celular;
        }
        if (direccion != null) {
            this.direccion = direccion;
        }
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public boolean esAdmin() {
        return this.rol == RolEmpleado.ADMIN;
    }

}
