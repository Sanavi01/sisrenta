package com.jirehcompanyit.sisrenta.controller.exception;

import com.jirehcompanyit.sisrenta.domain.exceptions.alquiler.AlquilerNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.ClienteEstaActivoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.ClienteNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.ClienteYaExisteException;
import com.jirehcompanyit.sisrenta.domain.exceptions.cliente.NuevoCelularEsIgualAlAnteriorException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoEstaActivoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoNoEncontradoException;
import com.jirehcompanyit.sisrenta.domain.exceptions.empleado.EmpleadoYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteYaExisteException.class)
    public ResponseEntity<String> handleClienteYaExiste(ClienteYaExisteException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<String> handleClienteNoEncontrado(ClienteNoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmpleadoYaExisteException.class)
    public ResponseEntity<String> handleEmpleadoYaExiste(EmpleadoYaExisteException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<String> handleEmpleadoNoEncontrado(EmpleadoNoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NuevoCelularEsIgualAlAnteriorException.class)
    public ResponseEntity<String> handleNuevoCelularEsIgualAlAnterior(NuevoCelularEsIgualAlAnteriorException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ClienteEstaActivoException.class)
    public ResponseEntity<String> handleClienteEstaActivo(ClienteEstaActivoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmpleadoEstaActivoException.class)
    public ResponseEntity<String> handleEmpleadoEstaActivo(EmpleadoEstaActivoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(AlquilerNoEncontradoException.class)
    public ResponseEntity<String> handleAlquilerNoEncontrado(AlquilerNoEncontradoException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
