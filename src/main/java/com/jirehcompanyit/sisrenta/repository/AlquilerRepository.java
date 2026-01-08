package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.domain.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlquilerRepository extends JpaRepository<Alquiler,Integer> {
}
