package com.jirehcompanyit.sisrenta.controller.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarClienteRequestDTO {

    @NotBlank(message = "No se registro el nombre del cliente")
    private String nombre;
    @NotBlank(message = "No se registro el apellido del cliente")
    private String apellido;
    @NotBlank(message = "No se registro el celular del cliente.")
    private String celular;
}
