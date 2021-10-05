package br.com.zup.edu.dojo.orquestrador.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> MethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<Error> erros = e.getBindingResult().getFieldErrors().stream().map(Error::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> IllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ValidationErrorsOutputDto(e.getMessage()));
    }
}
