package com.jirehcompanyit.sisrenta.domain.exceptions.empleado;

public class EmpleadoYaExisteException extends RuntimeException {
    public EmpleadoYaExisteException(String message) {
        super(message);
    }
}
