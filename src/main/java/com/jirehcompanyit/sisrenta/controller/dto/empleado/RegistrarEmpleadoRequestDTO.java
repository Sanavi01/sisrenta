package com.jirehcompanyit.sisrenta.controller.dto.empleado;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrarEmpleadoRequestDTO {
    @NotBlank(message = "No se registro el nombre del empleado")
    private String nombre;
    @NotBlank(message = "No se registro el apellido del empleado")
    private String apellido;
    @NotBlank(message = "No se registro el celular del empleado")
    private String celular;
    @NotBlank(message = "No se registro la direccion del empleado")
    private String direccion;

}
