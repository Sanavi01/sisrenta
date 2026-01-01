package com.jirehcompanyit.sisrenta.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ClienteResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
}
