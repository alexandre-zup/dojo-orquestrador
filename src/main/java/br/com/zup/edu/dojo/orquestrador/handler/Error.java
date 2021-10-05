package br.com.zup.edu.dojo.orquestrador.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class Error {

    @JsonProperty
    private final String campo;
    @JsonProperty
    private final String mensagem;

    public Error(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }

    public Error(String mensagem, String campo) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}