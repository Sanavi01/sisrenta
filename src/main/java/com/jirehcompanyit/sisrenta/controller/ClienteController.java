package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.cliente.ActualizarCelularClienteRequest;
import com.jirehcompanyit.sisrenta.controller.dto.cliente.ClienteResponse;
import com.jirehcompanyit.sisrenta.controller.dto.cliente.RegistrarClienteRequest;
import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ClienteResponse> registrarCliente(
            @Valid @RequestBody RegistrarClienteRequest request) {
        Cliente cliente = clienteService.registrarCliente(
                request.getNombre(),
                request.getApellido(),
                request.getCelular()
        );

        ClienteResponse response = new ClienteResponse(
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
    public ResponseEntity<ClienteResponse> buscarClientePorCelular(
            @Valid @RequestParam String celular) {
        Cliente cliente = clienteService.buscarClientePorCelular(celular);

        ClienteResponse response = new ClienteResponse(
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
    public ResponseEntity<ClienteResponse> actualizarCelularCliente(
            @PathVariable("id") Long id,
            @Valid @RequestBody ActualizarCelularClienteRequest request) {

        System.out.println("id Controller: " + id);
        Cliente clienteEditado = clienteService.actualizarCelular(
                id,
                request.getNuevoCelular()
        );

        ClienteResponse response = new ClienteResponse(
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
    public ResponseEntity<ClienteResponse> activarCliente(
            @PathVariable("id") Long id) {

        Cliente clienteActivado = clienteService.activarCliente(id);

        ClienteResponse response = new ClienteResponse(
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
    public ResponseEntity<ClienteResponse> desactivarCliente(
            @PathVariable("id") Long id) {

        Cliente clienteDesactivado = clienteService.desactivarCliente(id);

        ClienteResponse response = new ClienteResponse(
                clienteDesactivado.getId(),
                clienteDesactivado.getNombre(),
                clienteDesactivado.getApellido(),
                clienteDesactivado.getCelular()
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
