package com.GlobalSolution.DDD.Roadie.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // --- USUÁRIOS ---

    @ExceptionHandler(UsuarioJaExistenteException.class)
    public ResponseEntity<String> handleUsuarioJaExistente(UsuarioJaExistenteException ex){
        logErro(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        logErro(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // --- TRILHAS ---

    @ExceptionHandler(TrilhaJaExistenteException.class)
    public ResponseEntity<String> handleTrilhaJaExistente(TrilhaJaExistenteException ex){
        logErro(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TrilhaNaoEncontradaException.class)
    public ResponseEntity<String> handleTrilhaNaoEncontrada(TrilhaNaoEncontradaException ex){
        logErro(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // --- FALLBACK PARA QUALQUER OUTRO ERRO ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOutrosErros(Exception ex){
        logErro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado");
    }


    // --- FUNÇÃO PARA "EMBELEZAR" MENSAGEM DE ERRO EM REQUISIÇÃO ---
    private void logErro(HttpStatus status, String mensagem){
        logger.error("Status {}: {}", status.value(), mensagem);
    }
}
