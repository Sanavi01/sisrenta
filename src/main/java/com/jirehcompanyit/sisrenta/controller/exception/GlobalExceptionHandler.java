package com.jirehcompanyit.sisrenta.controller.exception;

import com.jirehcompanyit.sisrenta.domain.exceptions.ClienteYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(ClienteYaExisteException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
