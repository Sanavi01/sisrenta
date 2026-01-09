package com.jirehcompanyit.sisrenta.controller.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarCelularClienteRequest {
    @NotBlank
    private String nuevoCelular;
}
