package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DetallesAlquilerResponse {

    private Long id;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaLimiteDevolucion;
    private LocalDateTime fechaDevolucion;

    private String itemReferencia;

    public static DetallesAlquilerResponse fromEntity(Alquiler alquiler) {

        String itemReferencia = null;

        if (!alquiler.getItemsAlquiler().isEmpty()) {
            itemReferencia = alquiler.getItemsAlquiler()
                    .getFirst()
                    .getNombreTraje();
        }

        return new DetallesAlquilerResponse(
                alquiler.getId(),
                alquiler.getEstadoAlquiler().name(),
                alquiler.getFechaCreacion(),
                alquiler.getFechaEntrega(),
                alquiler.getFechaLimiteDevolucion(),
                alquiler.getFechaDevolucion(),
                itemReferencia
        );
    }
}
