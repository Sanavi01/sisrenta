package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.enums.RolEmpleado;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoEstaActivoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoYaExisteException;
import com.jirehcompanyit.sisrenta.domain.model.Empleado;
import com.jirehcompanyit.sisrenta.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado registrarEmpleado(String nombre, String apellido, String celular, String direccion) {

        System.out.println("Cliente llegado Service: " + nombre);
        empleadoRepository.findByCelular(celular)
                .ifPresent(empleadoEncontrado -> {
                    throw new EmpleadoYaExisteException(
                            "El empleado ya existe en el sistema"
                                    + " "
                                    + empleadoEncontrado.getNombre()
                                    + " "
                                    + empleadoEncontrado.getApellido()
                    );
                });


        Empleado empleado = Empleado.builder()
                .nombre(nombre)
                .apellido(apellido)
                .celular(celular)
                .direccion(direccion)
                .rol(RolEmpleado.EMPLEADO)
                .build();

        return empleadoRepository.save(empleado);
    }

    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("No hay ningun empleado con el id:" + id));
    }

    public Empleado buscarEmpleadoPorCelular(String celular) {
        return empleadoRepository.findByCelular(celular)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("No hay ningun empleado registrado con numero de celular " + celular));
    }

    public Empleado actualizarEmpleado(Long id, String celular, String direccion) {

        Empleado empleadoActualizar = buscarEmpleadoPorId(id);

        if (celular != null) {
            empleadoRepository.findByCelular(celular)
                    .filter(e -> !e.getId().equals(empleadoActualizar.getId()))
                    .ifPresent(e -> {
                        throw new EmpleadoYaExisteException(
                                "El celular ya pertenece a otro empleado"
                        );
                    });
        }
        empleadoActualizar.actualizarEmpleado(celular, direccion);

        return empleadoRepository.save(empleadoActualizar);
    }

    public Empleado activarEmpleado(Long id) {

        Empleado empleadoEncontrado = buscarEmpleadoPorId(id);
        if (empleadoEncontrado.isActivo()) {
            throw new EmpleadoEstaActivoException(
                    "El empleado "
                            + empleadoEncontrado.getNombre()
                            + " "
                            + empleadoEncontrado.getApellido()
                            + " ya esta activo en el sistema"
            );
        }
        empleadoEncontrado.activar();

        return empleadoRepository.save(empleadoEncontrado);
    }

    public Empleado desactivarEmpleado(Long id) {
        Empleado empleadoEncontrado = buscarEmpleadoPorId(id);
        if (!empleadoEncontrado.isActivo()) {
            throw new EmpleadoEstaActivoException(
                    "El empleado "
                            + empleadoEncontrado.getNombre()
                            + " "
                            + empleadoEncontrado.getApellido()
                            + " ya esta desactivado en el sistema"
            );
        }
        empleadoEncontrado.desactivar();

        return empleadoRepository.save(empleadoEncontrado);
    }


}
