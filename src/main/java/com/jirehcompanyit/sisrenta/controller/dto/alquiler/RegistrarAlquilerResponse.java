package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RegistrarAlquilerResponse {

    private Long cliente_id;
    private Long empleado_id;
    private Long alquiler_id;
    private LocalDateTime fechaCreacion;
}
