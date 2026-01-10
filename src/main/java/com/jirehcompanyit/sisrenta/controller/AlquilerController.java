package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.alquiler.RegistrarAlquilerRequest;
import com.jirehcompanyit.sisrenta.controller.dto.alquiler.RegistrarAlquilerResponse;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import com.jirehcompanyit.sisrenta.service.AlquilerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController (AlquilerService alquilerService){
        this.alquilerService = alquilerService;
    }

    @PostMapping
    public ResponseEntity<RegistrarAlquilerResponse> registrarAlquiler(
            @RequestBody RegistrarAlquilerRequest request){

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
}
