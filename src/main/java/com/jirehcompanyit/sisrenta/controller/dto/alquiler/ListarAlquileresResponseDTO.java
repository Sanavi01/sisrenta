package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ListarAlquileresResponseDTO {

    private Long id;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaLimiteDevolucion;
    private LocalDateTime fechaDevolucion;

    private String itemReferencia;

    private Integer valorTotal;

    public static ListarAlquileresResponseDTO fromEntity(Alquiler alquiler) {

        String itemReferencia = null;

        if (!alquiler.getItemsAlquiler().isEmpty()) {
            itemReferencia = alquiler.getItemsAlquiler()
                    .getFirst()
                    .getNombreTraje().concat(" +" + alquiler.getItemsAlquiler().size() + " items");
        }

        return new ListarAlquileresResponseDTO(
                alquiler.getId(),
                alquiler.getEstadoAlquiler().name(),
                alquiler.getFechaCreacion(),
                alquiler.getFechaEntrega(),
                alquiler.getFechaLimiteDevolucion(),
                alquiler.getFechaDevolucion(),
                itemReferencia,
                alquiler.calcularTotal()
        );
    }
}
