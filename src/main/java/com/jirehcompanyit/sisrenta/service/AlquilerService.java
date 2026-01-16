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

/**
 * Servicio de la aplicacion responsable de los casos de uso de los alquileres
 *
 * <p>Este servicio coordina la interaccion enttre entidades de dpminio, repositorios y
 * validaciones ncesarias para ejecutar operaciones sobre alquileres, sin contener
 * logica de negocio propia</p>
 *
 * <p> La logica de negocio y validaciones especificas el estado del cliente se delegan
 * en la entidad {@link Empleado}</p>
 *
 * @author Santiago Angarita Avila
 */
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

    /**
     * Registrar Alquiler
     * <p>Este metodo unicamente crea el alquiler en la base de datos,
     * asociando al cliente y al empleado. NO CREA ITEMS, solo crea el alquiler</p>
     *
     * <p>Validaciones:</p>
     * <ul>
     *     <li>Si existe el cliente en la BBDD</li>
     *     <li>Si el cliente esta activo en el sistema</li>
     *     <li>Si existe el empleado en la BBDD</li>
     *     <li>Si el empleado esta activo en el sistema</li>
     * </ul>
     *
     * @param clienteId  Identificador del cliente al que se asociara el alquiler
     * @param empleadoId Identificador del empleado al que se le asociara el alquiler
     * @return Alquiler registrado
     * @throws ClienteNoEncontradoException  Si el cliente no es encontrado con el Id otorgado
     * @throws EmpleadoNoEncontradoException Si el empleado no es encontrado con el Id otorgado
     */
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

    /**
     * Agregar item Alquiler
     *
     * <p>Este metodo permite agregar items a un alquiler ya creado, agregandolo a una
     * lista. EL metodo de agregarItem se encuentra en Alquiler</p>
     * <p>
     * Validaciones desde el dominio
     * <ul>
     *     <li>Desde {@link Alquiler} se valida que el alquiler haya sido creado y se mantenga en este estado</li>
     *     <li>Desde {@link ItemAlquiler se valida que la cantidad y los precios que sean superiores a 0}</li>
     * </ul>
     *
     * @param alquilerId     Identificador de el alquiler al cual se le va a asociar el item
     * @param nombreTraje    Nombre que se le pondra al item (traje)
     * @param descripcion    Descripcion que se le dara al item (Que compone el traje)
     * @param cantidad       Cantidad de este item a llevar
     * @param precioUnitario Valor que tiene el item a llevar
     * @return itemAlquiler registrado
     */
    public ItemAlquiler agregarItemAlquiler(Long alquilerId, String nombreTraje, String descripcion, Integer cantidad, Integer precioUnitario) {

        Alquiler alquiler = buscarAlquilerPorId(alquilerId);

        alquiler.agregarItem(nombreTraje, descripcion, cantidad, precioUnitario);

        alquilerRepository.save(alquiler);

        return alquiler.getItemsAlquiler().getLast();
    }

    /**
     * Listar alquileres por cliente
     *
     * <p> El metodo busca si existe un cliente con el ID proporcionado
     * Luego busca los alquileres asociados a ese ID, agregandolos a una lista de alquileres
     * </p>
     * <p>
     * Validaciones
     * <li>El Id proporcionado pertenece a un cliente</li>
     * <li>El cliente tiene alquileres registrados</li>
     *
     * @param clienteId Identificador del cliente al cual se le buscaran los alquileres
     * @return List<Alquiler> alquileres
     * @throws ClienteNoEncontradoException  Si no se encontro un cliente con el id proporcionado
     * @throws AlquilerNoEncontradoException Si el cliente no cuenta con alquileres registrados con su ID
     */
    public List<Alquiler> listarAlquileresPorCliente(Long clienteId) {

        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado"));

        List<Alquiler> alquileres = alquilerRepository.findAllByClienteId(clienteId);
        if (alquileres.isEmpty()) {
            throw new AlquilerNoEncontradoException("El cliente no cuenta con facturas registradas");
        }

        return alquileres;
    }

    /**
     * Entregar Alquiler
     * <p>
     * Validaciones:
     * <ul>
     *     <li>Se verifica que exista un alquiler con el ID dado (Desde el service)</li>
     *     <li>Se verifica que el alquiler se encuentre marcado como Creado, ningun
     *     alquiler puede ser marcado como entregado en alguna fase posterior para evitar
     *     incongruencias (Se valida desde el dominio)</li>
     * </ul>
     *
     * @param id Identificador del alquiler el cual se marcara como entregado
     * @return Alquiler marcado como entregado
     */

    public Alquiler entregarAlquiler(Long id) {
        Alquiler alquiler = buscarAlquilerPorId(id);

        alquiler.marcarComoEntregado();

        return alquiler;
    }

    /**
     * Cancelar Alquiler
     * <p>
     * Validaciones:
     * <ul>
     *     <li>Se verifica que exista un alquiler con el ID dado (Desde el service)</li>
     *     <li>Se verifica que el alquiler se encuentre marcado como Creado, ningun
     *     alquiler puede ser marcado como Cancelado en alguna fase posterior para evitar
     *     incongruencias (Se valida desde el dominio)</li>
     * </ul>
     *
     * @param id Identificador del alquiler el cual se marcara como cancelado
     * @return Alquiler marcado como cancelado
     */

    public Alquiler cancelarAlquiler(Long id) {
        Alquiler alquiler = buscarAlquilerPorId(id);

        alquiler.marcarComoCancelado();

        return alquiler;
    }
}
