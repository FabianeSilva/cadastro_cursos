package com.fabianeSilva.cadastro_cursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> tratarRuntimeException(RuntimeException exception){

        Map<String, String> resposta = new HashMap<>();

        resposta.put("messagem", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarValidacao(MethodArgumentNotValidException exception){

        Map<String, String> resposta = new HashMap<>();

        String mensagem = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        resposta.put("mensagem", mensagem);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
