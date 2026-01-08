package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.domain.enums.RolEmpleado;
import com.jirehcompanyit.sisrenta.domain.exceptions.EmpleadoYaExisteException;
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

        System.out.println("Cliente llegado Service: " +  nombre);
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


}
