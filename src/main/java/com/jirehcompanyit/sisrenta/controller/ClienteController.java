package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.cliente.ActualizarCelularClienteRequestDTO;
import com.jirehcompanyit.sisrenta.controller.dto.cliente.ClienteResponseDTO;
import com.jirehcompanyit.sisrenta.controller.dto.cliente.RegistrarClienteRequestDTO;
import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Gestion de Clientes del Sistema")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ---------------------------
    // POST /clientes
    // ---------------------------
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> registrarCliente(
            @Valid @RequestBody RegistrarClienteRequestDTO request) {
        Cliente cliente = clienteService.registrarCliente(
                request.getNombre(),
                request.getApellido(),
                request.getCelular()
        );

        ClienteResponseDTO response = new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ---------------------------
    // GET /clientes?celular=...
    // ---------------------------
    @GetMapping
    public ResponseEntity<ClienteResponseDTO> buscarClientePorCelular(
            @Valid @RequestParam String celular) {
        Cliente cliente = clienteService.buscarClientePorCelular(celular);

        ClienteResponseDTO response = new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCelular()
        );
        return ResponseEntity.ok(response);
    }

    // ---------------------------
    // PATCH /clientes/id
    // ---------------------------

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarCelularCliente(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActualizarCelularClienteRequestDTO request) {

        System.out.println("id Controller: " + id);
        Cliente clienteEditado = clienteService.actualizarCelular(
                id,
                request.getNuevoCelular()
        );

        ClienteResponseDTO response = new ClienteResponseDTO(
                clienteEditado.getId(),
                clienteEditado.getNombre(),
                clienteEditado.getApellido(),
                clienteEditado.getCelular()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // ---------------------------
    // PATCH /clientes/id/activar
    // ---------------------------

    @PatchMapping("/{id}/activar")
    public ResponseEntity<ClienteResponseDTO> activarCliente(
            @PathVariable("id") Long id) {

        Cliente clienteActivado = clienteService.activarCliente(id);

        ClienteResponseDTO response = new ClienteResponseDTO(
                clienteActivado.getId(),
                clienteActivado.getNombre(),
                clienteActivado.getApellido(),
                clienteActivado.getCelular()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // ---------------------------
    // PATCH /clientes/id/desactivar
    // -------

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ClienteResponseDTO> desactivarCliente(
            @PathVariable("id") Long id) {

        Cliente clienteDesactivado = clienteService.desactivarCliente(id);

        ClienteResponseDTO response = new ClienteResponseDTO(
                clienteDesactivado.getId(),
                clienteDesactivado.getNombre(),
                clienteDesactivado.getApellido(),
                clienteDesactivado.getCelular()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
