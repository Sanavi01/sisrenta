package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.repository.ClienteRepository;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(String nombre, String apellido, String celular){
        if(clienteRepository.existsByCelular(celular)){
            Cliente clienteEncontrado = clienteRepository.findClienteByCelular(celular);
            throw new IllegalStateException("Este numero ya ha sido registrado en el cliente" + clienteEncontrado.getNombre() + " " + clienteEncontrado.getApellido());
        }
        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .celular(celular)
                .build();

        return clienteRepository.save(cliente);
    }

}
