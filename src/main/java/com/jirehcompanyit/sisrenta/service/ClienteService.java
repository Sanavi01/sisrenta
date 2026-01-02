package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.exceptions.ClienteNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.ClienteYaExisteException;
import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(String nombre, String apellido, String celular) {

        clienteRepository.findClienteByCelular(celular)
                .ifPresent(clienteEncontrado ->{
                    throw new ClienteYaExisteException(
                            "Este numero ya ha sido registrado en el cliente"
                            + clienteEncontrado. getNombre()
                            + " "
                            + clienteEncontrado.getApellido()
                    );
                });

        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .celular(celular)
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCelular(String celular){
        return clienteRepository.findClienteByCelular(celular)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
    }

}
