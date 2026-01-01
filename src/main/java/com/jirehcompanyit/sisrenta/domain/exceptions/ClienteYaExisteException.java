package com.jirehcompanyit.sisrenta.domain.exceptions;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String message) {
        super(message);
    }
}
