package com.jirehcompanyit.sisrenta.dto.empleado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "No se proporciono el usuario para el login")
    private String username;
    @NotBlank(message = "No se proporcino la contraseña del usuario")
    private String password;
}
