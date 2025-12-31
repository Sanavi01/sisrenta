package com.jirehcompanyit.sisrenta.repository;

import com.jirehcompanyit.sisrenta.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCelular(String celular);

    public Cliente findClienteByCelular(String celular);


}
