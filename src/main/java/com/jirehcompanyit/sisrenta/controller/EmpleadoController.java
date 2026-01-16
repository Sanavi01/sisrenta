package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.empleado.ActualizarEmpleadoRequest;
import com.jirehcompanyit.sisrenta.controller.dto.empleado.EmpleadoResponse;
import com.jirehcompanyit.sisrenta.controller.dto.empleado.RegistrarEmpleadoRequest;
import com.jirehcompanyit.sisrenta.domain.model.Empleado;
import com.jirehcompanyit.sisrenta.service.EmpleadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Empleado", description = "Gestion de empleados del sistema")
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

    @GetMapping()
    public ResponseEntity<EmpleadoResponse> buscarEmpleadoPorCelular(
            @Valid @RequestParam String celular) {
        Empleado empleadoEncontrado = empleadoService.buscarEmpleadoPorCelular(celular);

        EmpleadoResponse response = new EmpleadoResponse(
                empleadoEncontrado.getId(),
                empleadoEncontrado.getNombre(),
                empleadoEncontrado.getApellido(),
                empleadoEncontrado.getCelular(),
                empleadoEncontrado.getDireccion()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> actualizarEmpleado(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActualizarEmpleadoRequest request) {

        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(
                id,
                request.getCelular(),
                request.getDireccion()
        );

        EmpleadoResponse response = new EmpleadoResponse(
                empleadoActualizado.getId(),
                empleadoActualizado.getNombre(),
                empleadoActualizado.getApellido(),
                empleadoActualizado.getCelular(),
                empleadoActualizado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body((response));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<EmpleadoResponse> activarEmpleado(
            @PathVariable("id") Long id) {

        Empleado empleado = empleadoService.activarEmpleado(id);

        EmpleadoResponse response = new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<EmpleadoResponse> desactivarEmpleado(
            @PathVariable("id") Long id) {

        Empleado empleado = empleadoService.desactivarEmpleado(id);

        EmpleadoResponse response = new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
