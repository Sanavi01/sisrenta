package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RegistrarAlquilerRequest {

    @NotNull(message = "No se asigno un id de un cliente")
    private Long clienteId;
    @NotNull(message = "No se asigno un id de un empleado")
    private Long empleadoId;
}
