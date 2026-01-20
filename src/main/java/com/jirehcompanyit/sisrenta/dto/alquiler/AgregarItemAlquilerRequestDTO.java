package com.jirehcompanyit.sisrenta.dto.alquiler;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AgregarItemAlquilerRequestDTO {

    @NotBlank(message = "No se le asigno un nombre al traje")
    private String nombreTraje;
    private String descripcion;
    @NotNull
    @Min(value = 1, message = "La cantidad tiene que ser mayor o igual a 1")
    private Integer cantidad;
    @NotNull
    @Min(value = 1, message = "El precio tiene que ser mayor a 1 ")
    private Integer precioUnitario;
}
