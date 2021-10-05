package br.com.zup.edu.dojo.orquestrador.deposito;

import br.com.zup.edu.dojo.orquestrador.clients.TipoTransacao;
import br.com.zup.edu.dojo.orquestrador.clients.TransacaoRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class DepositoRequest {
    @NotBlank
    @JsonProperty
    private String numeroDaConta;
    @NotNull
    @Positive
    @JsonProperty
    private BigDecimal valor;

    public TransacaoRequest paraTransacao() {
        return new TransacaoRequest(numeroDaConta, TipoTransacao.CREDITO, valor);
    }
}
