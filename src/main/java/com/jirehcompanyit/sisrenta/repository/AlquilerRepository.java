package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlquilerRepository extends JpaRepository<Alquiler,Integer> {

    Optional<Alquiler> findById(Long id);
}
