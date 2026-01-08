package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCelular(String celular);

    Optional<Cliente> findClienteByCelular(String celular);

    Optional <Cliente> findById(Long clienteId);
}
