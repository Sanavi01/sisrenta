package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import com.jirehcompanyit.sisrenta.controller.dto.item_alquiler.ItemAlquilerResponse;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class DetalleAlquilerResponse {

    private Long id;
    private String nombreCliente;
    private String nombreEmpleado;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCancelacion;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaLimiteDevolucion;
    private LocalDateTime fechaDevolucion;

    private String estado;

    private List<ItemAlquilerResponse> itemsAlquiler;

    public static DetalleAlquilerResponse fromEntity(Alquiler alquiler) {

        List<ItemAlquilerResponse> itemsAlquiler = alquiler.getItemsAlquiler()
                .stream()
                .map(ItemAlquilerResponse::fromEntity)
                .toList();

        return new DetalleAlquilerResponse(
                alquiler.getId(),
                alquiler.getCliente().getNombre().concat(" ".concat(alquiler.getCliente().getApellido())),
                alquiler.getEmpleado().getNombre().concat(" ").concat(alquiler.getEmpleado().getApellido()),
                alquiler.getFechaCreacion(),
                alquiler.getFechaCancelacion(),
                alquiler.getFechaEntrega(),
                alquiler.getFechaLimiteDevolucion(),
                alquiler.getFechaDevolucion(),
                alquiler.getEstadoAlquiler().name(),
                itemsAlquiler
        );

    }


}
