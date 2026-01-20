package com.jirehcompanyit.sisrenta.dto.alquiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RegistrarAlquilerResponseDTO {

    private Long cliente_id;
    private Long empleado_id;
    private Long alquiler_id;
    private LocalDateTime fechaCreacion;
}
