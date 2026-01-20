package com.jirehcompanyit.sisrenta.exception.cliente;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}
