package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.exceptions.ClienteNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.EmpleadoNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.domain.model.Empleado;
import com.jirehcompanyit.sisrenta.repository.AlquilerRepository;
import com.jirehcompanyit.sisrenta.repository.ClienteRepository;
import com.jirehcompanyit.sisrenta.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public AlquilerService(AlquilerRepository alquilerRepository, ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository) {
        this.alquilerRepository = alquilerRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Alquiler registrarAlquiler(Long clienteId, Long empleadoId){

        Cliente clienteEncontrado = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));

        Empleado empleadoEncontrado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado"));

        Alquiler alquiler = Alquiler.builder()
                .cliente(clienteEncontrado)
                .empleado(empleadoEncontrado)
                .build();
        return  alquilerRepository.save(alquiler);
    }
}
