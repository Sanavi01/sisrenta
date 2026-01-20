package com.jirehcompanyit.sisrenta.controller.dto.item_alquiler;

import com.jirehcompanyit.sisrenta.domain.model.ItemAlquiler;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemAlquilerResponseDTO {

    private Long id;
    private String nombreTraje;
    private String descripcion;
    private Integer cantidad;
    private Integer precioUnitario;

    public static ItemAlquilerResponseDTO fromEntity(ItemAlquiler item) {
        return new ItemAlquilerResponseDTO(
                item.getId(),
                item.getNombreTraje(),
                item.getDescripcion(),
                item.getCantidad(),
                item.getPrecioUnitario()
        );
    }
}
