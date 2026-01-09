package com.jirehcompanyit.sisrenta.controller.exception;

import com.jirehcompanyit.sisrenta.domain.exceptions.*;
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
}
