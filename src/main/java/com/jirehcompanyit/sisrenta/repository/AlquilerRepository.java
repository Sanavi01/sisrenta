package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    Optional<Alquiler> findById(Long id);

    List<Alquiler> findAllByClienteId(Long clienteId);

}
