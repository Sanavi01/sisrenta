package com.jirehcompanyit.sisrenta.service;

import com.jirehcompanyit.sisrenta.exception.cliente.ClienteEstaActivoException;
import com.jirehcompanyit.sisrenta.exception.cliente.ClienteNoEncontradoException;
import com.jirehcompanyit.sisrenta.exception.cliente.ClienteYaExisteException;
import com.jirehcompanyit.sisrenta.model.Cliente;
import com.jirehcompanyit.sisrenta.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Servicio de aplicación responsable de orquestar los casos de uso
 * relacionados con los clientes.
 *
 * <p>Este servicio coordina la interacción entre entidades de dominio,
 * repositorios y validaciones necesarias para ejecutar operaciones
 * sobre clientes, sin contener lógica de negocio propia.</p>
 *
 * <p>La lógica de negocio y validaciones específicas del estado del cliente
 * se delegan a la entidad {@link Cliente}.</p>
 *
 * @author Santiago Angarita Avila
 */

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Registra un cliente
     *
     * <p>El servicio valida que el numero no este registrado con otro cliente </p>
     *
     * @param nombre   Nombre del usuario a registrar
     * @param apellido Apellido del usuario a registrar
     * @param celular  Celular del usuario a registrar
     * @return Cliente Registrado
     */

    public Cliente registrarCliente(String nombre, String apellido, String celular) {

        clienteRepository.findClienteByCelular(celular)
                .ifPresent(clienteEncontrado -> {
                    throw new ClienteYaExisteException(
                            "Este numero ya ha sido registrado en el cliente"
                                    + clienteEncontrado.getNombre()
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

    public Cliente buscarClientePorCelular(String celular) {
        return clienteRepository.findClienteByCelular(celular)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con el celular " + celular));
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));
    }

    /**
     * Actualizar informacion del cliente (celular)
     *
     * <p>Se hace una validacion si el cliente existe, y si este existe, que el numero de celular
     * que se quiere asociar a este no este asociado a otro cliente</p>
     *
     * @param id           Identificador del Cliente
     * @param nuevoCelular El nuevo celular que se quiere asociar al cliente
     * @return Cliente actualizado
     * @throws ClienteYaExisteException si el celular ya pertenece a otro cliente
     */

    public Cliente actualizarCelular(Long id, String nuevoCelular) {
        Cliente clienteEncontrado = buscarClientePorId(id);

        clienteRepository.findClienteByCelular(nuevoCelular)
                .filter(c -> !c.getId().equals(clienteEncontrado.getId()))
                .ifPresent(c -> {
                    throw new ClienteYaExisteException(
                            "El celular ya pertenece a otro cliente"
                    );
                });

        clienteEncontrado.actualizarCelular(nuevoCelular);
        return clienteRepository.save(clienteEncontrado);
    }

    /**
     * Desactivar Cliente
     * <ul>
     *     <li>Valida si el cliente existe a traves del id</li>
     *     <li>Valida si el cliente ya esta desactivado</li>
     * </ul>
     *
     * @param id Identificador del id
     * @return Cliente desactivado
     * @throws ClienteEstaActivoException Si el cliente esta desactivado
     */

    public Cliente desactivarCliente(Long id) {
        Cliente clienteEncontrado = buscarClientePorId(id);
        if (!clienteEncontrado.isActivo()) {
            throw new ClienteEstaActivoException(
                    "El cliente "
                            + clienteEncontrado.getNombre()
                            + " "
                            + clienteEncontrado.getApellido()
                            + " ya esta desactivado ");
        }
        clienteEncontrado.desactivar();
        return clienteRepository.save(clienteEncontrado);
    }

    /**
     * Activar Cliente
     * <ul>
     *     <li>Valida si el cliente existe a traves del id</li>
     *     <li>Valida si el cliente ya esta activo</li>
     * </ul>
     *
     * @param id Identificador del id
     * @return Cliente activado
     * @throws ClienteEstaActivoException Si el cliente esta activo
     */

    public Cliente activarCliente(Long id) {
        Cliente clienteEncontrado = buscarClientePorId(id);
        if (clienteEncontrado.isActivo()) {
            throw new ClienteEstaActivoException(
                    "El cliente "
                            + clienteEncontrado.getNombre()
                            + " "
                            + clienteEncontrado.getApellido()
                            + " ya esta activo ");
        }
        clienteEncontrado.activar();
        return clienteRepository.save(clienteEncontrado);
    }

}
