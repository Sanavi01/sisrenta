package com.jirehcompanyit.sisrenta.controller.dto.empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String direccion;
}
