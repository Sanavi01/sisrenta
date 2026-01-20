package com.jirehcompanyit.sisrenta.security;

import com.jirehcompanyit.sisrenta.model.Empleado;
import com.jirehcompanyit.sisrenta.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Empleado empleado = empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Empleado no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                empleado.getUsername(),
                empleado.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + empleado.getRol().name()))
        );
    }
}

