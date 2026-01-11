package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.alquiler.AgregarItemAlquilerRequest;
import com.jirehcompanyit.sisrenta.controller.dto.alquiler.AgregarItemAlquilerResponse;
import com.jirehcompanyit.sisrenta.controller.dto.alquiler.RegistrarAlquilerRequest;
import com.jirehcompanyit.sisrenta.controller.dto.alquiler.RegistrarAlquilerResponse;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import com.jirehcompanyit.sisrenta.domain.model.ItemAlquiler;
import com.jirehcompanyit.sisrenta.service.AlquilerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @PostMapping
    public ResponseEntity<RegistrarAlquilerResponse> registrarAlquiler(
            @RequestBody RegistrarAlquilerRequest request) {

        System.out.println("Id recibido: " + request.getClienteId());
        Alquiler alquiler = alquilerService.registrarAlquiler(
                request.getClienteId(),
                request.getEmpleadoId()
        );

        RegistrarAlquilerResponse response = new RegistrarAlquilerResponse(
                alquiler.getCliente().getId(),
                alquiler.getEmpleado().getId(),
                alquiler.getId(),
                alquiler.getFechaCreacion()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/agregarItem")
    public ResponseEntity<AgregarItemAlquilerResponse> agregarItemAlquiler(
            @PathVariable("id") Long alquilerId,
            @Valid @RequestBody AgregarItemAlquilerRequest request) {

        ItemAlquiler itemAlquiler = alquilerService.agregarItemAlquiler(
                alquilerId,
                request.getNombreTraje(),
                request.getDescripcion(),
                request.getCantidad(),
                request.getPrecioUnitario()
        );

        AgregarItemAlquilerResponse response = new AgregarItemAlquilerResponse(
                alquilerId,
                itemAlquiler.getId(),
                itemAlquiler.getNombreTraje(),
                itemAlquiler.getDescripcion(),
                itemAlquiler.getCantidad(),
                itemAlquiler.getPrecioUnitario()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
