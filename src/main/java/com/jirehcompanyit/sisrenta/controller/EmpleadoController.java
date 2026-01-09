package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.empleado.EmpleadoResponse;
import com.jirehcompanyit.sisrenta.controller.dto.empleado.RegistrarEmpleadoRequest;
import com.jirehcompanyit.sisrenta.domain.model.Empleado;
import com.jirehcompanyit.sisrenta.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> registrarEmpleado(
            @RequestBody RegistrarEmpleadoRequest request) {

        System.out.println("Cliente enviado Controller: " + request.getNombre());
        Empleado empleado = empleadoService.registrarEmpleado(
                request.getNombre(),
                request.getApellido(),
                request.getCelular(),
                request.getDireccion()
        );

        EmpleadoResponse empleadoResponse = new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoResponse);
    }
}
