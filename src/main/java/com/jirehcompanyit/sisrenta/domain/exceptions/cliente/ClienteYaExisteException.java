package com.jirehcompanyit.sisrenta.domain.exceptions.cliente;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}
