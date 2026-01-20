package com.jirehcompanyit.sisrenta.controller.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
}
