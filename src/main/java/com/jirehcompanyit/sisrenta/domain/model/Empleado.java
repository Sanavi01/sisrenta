package com.jirehcompanyit.sisrenta.domain.model;

import com.jirehcompanyit.sisrenta.domain.enums.RolEmpleado;
import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolEmpleado rol;

    @Column(nullable = false)
    private boolean activo;

    @Builder
    public Empleado(String nombre, String apellido, String celular, String direccion, RolEmpleado rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.direccion = direccion;
        this.rol = rol;
        this.activo = true;
    }

    public void activar(){
        this.activo = true;
    }

    public void desactivar(){
        this.activo = false;
    }

    public boolean esAdmin (){
        return this.rol == RolEmpleado.ADMIN;
    }

}
