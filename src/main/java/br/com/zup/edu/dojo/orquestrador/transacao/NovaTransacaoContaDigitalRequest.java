package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.clients.contadigital.TransacaoContaDigitalRequest;
import br.com.zup.edu.dojo.orquestrador.kakfa.TipoTransacao;
import br.com.zup.edu.dojo.orquestrador.kakfa.TransacaoMensagem;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class NovaTransacaoContaDigitalRequest {
    @NotBlank
    @JsonProperty
    private String numeroConta;
    @NotNull
    @Positive
    @JsonProperty
    private BigDecimal valor;


    public NovaTransacaoContaDigitalRequest(String numeroConta, BigDecimal valor) {
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public TransacaoContaDigitalRequest paraTransacao(TipoTransacaoContaDigital tipoTransacao) {
        return new TransacaoContaDigitalRequest(numeroConta, tipoTransacao, valor);
    }

    public TransacaoMensagem paraTransacaoMensagem(UUID idCliente, TipoTransacao transacao) {
        return new TransacaoMensagem(transacao, valor, LocalDateTime.now(), idCliente, numeroConta);
    }

}
