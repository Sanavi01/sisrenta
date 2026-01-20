package com.jirehcompanyit.sisrenta.dto.empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarEmpleadoRequestDTO {

    private String celular;
    private String direccion;

}
