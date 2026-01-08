package com.jirehcompanyit.sisrenta.controller.dto;

import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RegistrarAlquilerResponse {

    private Long cliente_id;
    private Long empleado_id;
    private Long alquiler_id;
    private LocalDateTime fechaCreacion;
}
