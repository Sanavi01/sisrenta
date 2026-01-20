package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.enums.RolEmpleado;
import com.jirehcompanyit.sisrenta.exception.empleado.EmpleadoEstaActivoException;
import com.jirehcompanyit.sisrenta.exception.empleado.EmpleadoNoEncontradoException;
import com.jirehcompanyit.sisrenta.exception.empleado.EmpleadoYaExisteException;
import com.jirehcompanyit.sisrenta.model.Empleado;
import com.jirehcompanyit.sisrenta.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de la aplicacion responsable de los casos de uso relacionados con los empleados
 *
 * <p>Este servicio coordina la interaccion entre entidades de dominio, repositorios y
 * validaciones necesarias para ejecutar operaciones sobre empleados, sin contener
 * logica de negocio propia</p>
 *
 * <p>La logica de negocio y validaciones especificas del estado del cliente se delegan
 * en la entidad {@link Empleado}</p>
 *
 * @author Santiago Angarita Avila
 */

@Service
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Registrar Empleado
     *
     * <p>Validaciones</p>
     * <ul>
     *     <li>Si no existe un empleado con un celular ya asociado a otro empleado</li>
     * </ul>
     *
     * @param nombre    Nombres del empleado
     * @param apellido  Apellidos del empleado
     * @param celular   Celular del empleado
     * @param direccion Direccion del empleado
     * @return Cliente registrado
     * @throws EmpleadoYaExisteException Si ya existe un empleado que tenga el celular a registrar asociado
     */

    public Empleado registrarEmpleado(String nombre, String apellido, String celular, String direccion) {

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

    /**
     * Actualizar empleado
     *
     * <p>Permite actualizar la direccion y el numero de celular del cliente</p>
     *
     * <p>Validaciones</p>
     *
     * <ul>
     *     <li>Que el celular a cambiar no pertenezca a otro empleado</li>
     * </ul>
     *
     * @param id        Identificador del Empleado en BBDD
     * @param celular   Mumero de celular del empleado
     * @param direccion Direccion del empleado
     * @return Empleado actualizado
     * @throws EmpleadoYaExisteException Si el celular a cambiar ya esta asociado a otro cliente
     */

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

    /**
     * Activar Empleado
     * <ul>
     *     <li>Valida si el empleado existe a traves del id</li>
     *     <li>Valida si el empleado ya esta activo</li>
     * </ul>
     *
     * @param id Identificador del id
     * @return Empleado activado
     * @throws EmpleadoEstaActivoException Si el empleado esta activo
     */

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

    /**
     * Desactivar Empleado
     * <ul>
     *     <li>Valida si el empleado existe a traves del id</li>
     *     <li>Valida si el empleado ya esta desactivado</li>
     * </ul>
     *
     * @param id Identificador del id
     * @return Empleado desactivado
     * @throws EmpleadoEstaActivoException Si el empleado esta inactivo
     */
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
