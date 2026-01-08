package com.jirehcompanyit.sisrenta.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmpleadoResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String direccion;
}
