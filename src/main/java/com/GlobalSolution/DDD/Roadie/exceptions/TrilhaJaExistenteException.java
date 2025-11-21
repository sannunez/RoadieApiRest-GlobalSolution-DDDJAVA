package com.GlobalSolution.DDD.Roadie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
public class TrilhaJaExistenteException extends RuntimeException {
    public TrilhaJaExistenteException(String message) {
        super(message);
    }
}
