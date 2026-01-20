package com.jirehcompanyit.sisrenta.controller.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarCelularClienteRequest {
    @NotBlank(message = "No se digito el nuevo celular")
    private String nuevoCelular;
}
