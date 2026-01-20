package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.dto.empleado.ActualizarEmpleadoRequestDTO;
import com.jirehcompanyit.sisrenta.dto.empleado.EmpleadoResponseDTO;
import com.jirehcompanyit.sisrenta.dto.empleado.RegistrarEmpleadoRequestDTO;
import com.jirehcompanyit.sisrenta.model.Empleado;
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
    public ResponseEntity<EmpleadoResponseDTO> registrarEmpleado(
            @RequestBody RegistrarEmpleadoRequestDTO request) {

        System.out.println("Cliente enviado Controller: " + request.getNombre());
        Empleado empleado = empleadoService.registrarEmpleado(
                request.getNombre(),
                request.getApellido(),
                request.getCelular(),
                request.getDireccion(),
                request.getUsername(),
                request.getPassword()
        );

        EmpleadoResponseDTO empleadoResponse = new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoResponse);
    }

    @GetMapping()
    public ResponseEntity<EmpleadoResponseDTO> buscarEmpleadoPorCelular(
            @Valid @RequestParam String celular) {
        Empleado empleadoEncontrado = empleadoService.buscarEmpleadoPorCelular(celular);

        EmpleadoResponseDTO response = new EmpleadoResponseDTO(
                empleadoEncontrado.getId(),
                empleadoEncontrado.getNombre(),
                empleadoEncontrado.getApellido(),
                empleadoEncontrado.getCelular(),
                empleadoEncontrado.getDireccion()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualizarEmpleado(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActualizarEmpleadoRequestDTO request) {

        Empleado empleadoActualizado = empleadoService.actualizarEmpleado(
                id,
                request.getCelular(),
                request.getDireccion()
        );

        EmpleadoResponseDTO response = new EmpleadoResponseDTO(
                empleadoActualizado.getId(),
                empleadoActualizado.getNombre(),
                empleadoActualizado.getApellido(),
                empleadoActualizado.getCelular(),
                empleadoActualizado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body((response));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<EmpleadoResponseDTO> activarEmpleado(
            @PathVariable("id") Long id) {

        Empleado empleado = empleadoService.activarEmpleado(id);

        EmpleadoResponseDTO response = new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<EmpleadoResponseDTO> desactivarEmpleado(
            @PathVariable("id") Long id) {

        Empleado empleado = empleadoService.desactivarEmpleado(id);

        EmpleadoResponseDTO response = new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCelular(),
                empleado.getDireccion()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
