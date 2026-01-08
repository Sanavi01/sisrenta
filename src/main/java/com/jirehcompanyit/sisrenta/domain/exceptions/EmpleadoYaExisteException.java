package com.jirehcompanyit.sisrenta.domain.exceptions;

public class EmpleadoYaExisteException extends RuntimeException {
    public EmpleadoYaExisteException(String message) {
        super(message);
    }
}
