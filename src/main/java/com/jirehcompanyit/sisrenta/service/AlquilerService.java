package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.exceptions.alquiler.AlquilerNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.ClienteEstaActivoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.ClienteNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoEstaActivoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import com.jirehcompanyit.sisrenta.domain.model.Empleado;
import com.jirehcompanyit.sisrenta.domain.model.ItemAlquiler;
import com.jirehcompanyit.sisrenta.repository.AlquilerRepository;
import com.jirehcompanyit.sisrenta.repository.ClienteRepository;
import com.jirehcompanyit.sisrenta.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Alquiler registrarAlquiler(Long clienteId, Long empleadoId) {

        Cliente clienteEncontrado = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));

        Empleado empleadoEncontrado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado no encontrado"));

        if (!clienteEncontrado.isActivo()) {
            throw new ClienteEstaActivoException("El cliente esta inactivo en el sistema");
        }

        if (!empleadoEncontrado.isActivo()) {
            throw new EmpleadoEstaActivoException("El empleado esta inactivo en el sistema");
        }

        Alquiler alquiler = Alquiler.builder()
                .cliente(clienteEncontrado)
                .empleado(empleadoEncontrado)
                .build();
        return alquilerRepository.save(alquiler);
    }

    public Alquiler buscarAlquilerPorId(Long id) {
        return alquilerRepository.findById(id)
                .orElseThrow(() -> new AlquilerNoEncontradoException("No se ha encontrado ningun alquiler con el id " + id));
    }

    public ItemAlquiler agregarItemAlquiler(Long alquilerId, String nombreTraje, String descripcion, Integer cantidad, Integer precioUnitario) {

        Alquiler alquiler = buscarAlquilerPorId(alquilerId);

        alquiler.agregarItem(nombreTraje, descripcion, cantidad, precioUnitario);

        alquilerRepository.save(alquiler);

        return alquiler.getItemsAlquiler().getLast();
    }

    public List<Alquiler> listarAlquileresPorCliente(Long clienteId) {

        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));

        List<Alquiler> alquileres =alquilerRepository.findAllByClienteId(clienteId);
        if (alquileres.isEmpty()) {
            throw new AlquilerNoEncontradoException("El cliente no cuenta con facturas registradas");
        }

        return alquileres;
    }

}
