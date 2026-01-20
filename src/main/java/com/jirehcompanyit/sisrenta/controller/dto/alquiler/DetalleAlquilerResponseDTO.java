package com.jirehcompanyit.sisrenta.controller.dto.alquiler;

import com.jirehcompanyit.sisrenta.controller.dto.item_alquiler.ItemAlquilerResponseDTO;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class DetalleAlquilerResponseDTO {

    private Long id;
    private String nombreCliente;
    private String nombreEmpleado;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCancelacion;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaLimiteDevolucion;
    private LocalDateTime fechaDevolucion;

    private String estado;

    private List<ItemAlquilerResponseDTO> itemsAlquiler;

    private Integer valorTotal;

    public static DetalleAlquilerResponseDTO fromEntity(Alquiler alquiler) {

        List<ItemAlquilerResponseDTO> itemsAlquiler = alquiler.getItemsAlquiler()
                .stream()
                .map(ItemAlquilerResponseDTO::fromEntity)
                .toList();

        return new DetalleAlquilerResponseDTO(
                alquiler.getId(),
                alquiler.getCliente().getNombre().concat(" ".concat(alquiler.getCliente().getApellido())),
                alquiler.getEmpleado().getNombre().concat(" ").concat(alquiler.getEmpleado().getApellido()),
                alquiler.getFechaCreacion(),
                alquiler.getFechaCancelacion(),
                alquiler.getFechaEntrega(),
                alquiler.getFechaLimiteDevolucion(),
                alquiler.getFechaDevolucion(),
                alquiler.getEstadoAlquiler().name(),
                itemsAlquiler,
                alquiler.calcularTotal()
        );

    }


}
