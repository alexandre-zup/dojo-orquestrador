package br.com.zup.edu.dojo.orquestrador.clients.contadigital;

import br.com.zup.edu.dojo.orquestrador.transacao.TipoTransacaoContaDigital;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoContaDigitalRequest {

    @NotBlank
    @JsonProperty
    private String numeroConta;
    @NotNull
    @JsonProperty
    private TipoTransacaoContaDigital tipoDaTransacao;
    @NotNull
    @Positive
    @JsonProperty
    private BigDecimal valor;


    public TransacaoContaDigitalRequest(String numeroConta, TipoTransacaoContaDigital tipoDaTransacao, BigDecimal valor) {
        this.numeroConta = numeroConta;
        this.tipoDaTransacao = tipoDaTransacao;
        this.valor = valor;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public TipoTransacaoContaDigital getTipoDaTransacao() {
        return tipoDaTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

}