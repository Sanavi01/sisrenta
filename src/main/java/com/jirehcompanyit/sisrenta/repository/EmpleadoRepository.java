package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByCelular(String celular);
    Optional<Empleado> findByUsername(String username);
}
