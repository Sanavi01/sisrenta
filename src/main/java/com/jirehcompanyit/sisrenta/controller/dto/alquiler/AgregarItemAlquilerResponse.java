package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AgregarItemAlquilerResponse {

    private Long alquilerId;
    private Long itemAlquilerId;
    private String nombreTraje;
    private String descripcion;
    private Integer cantidad;
    private Integer precioUnitario;

}
