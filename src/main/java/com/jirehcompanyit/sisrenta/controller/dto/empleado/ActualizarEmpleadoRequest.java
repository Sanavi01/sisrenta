package com.jirehcompanyit.sisrenta.controller.dto.empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarEmpleadoRequest {

    private String celular;
    private String direccion;

}
