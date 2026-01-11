package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AgregarItemAlquilerRequest {

    @NotBlank
    private String nombreTraje;
    private String descripcion;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Integer precioUnitario;
}
