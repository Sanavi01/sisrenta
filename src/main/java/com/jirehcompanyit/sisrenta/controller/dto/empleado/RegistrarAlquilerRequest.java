package com.jirehcompanyit.sisrenta.controller.dto.empleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RegistrarAlquilerRequest {

    private Long clienteId;
    private Long empleadoId;
}
