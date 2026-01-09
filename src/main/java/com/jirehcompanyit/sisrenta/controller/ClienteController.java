package com.jirehcompanyit.sisrenta.controller;

import com.jirehcompanyit.sisrenta.controller.dto.ActualizarCelularClienteRequest;
import com.jirehcompanyit.sisrenta.controller.dto.ClienteResponse;
import com.jirehcompanyit.sisrenta.controller.dto.RegistrarClienteRequest;
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

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCelularCliente(
            @Valid @PathVariable("id") Long id,
            @RequestBody ActualizarCelularClienteRequest request) {

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
}
