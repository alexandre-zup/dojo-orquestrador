package br.com.zup.edu.dojo.orquestrador.kakfa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoMensagem {
    private TipoOperacao tipoOperacao;
    private BigDecimal valor;
    private LocalDateTime efetuadaEm;
    private UUID idCliente;
    private String numeroDaConta;

    public TransacaoMensagem() {
    }

    public TransacaoMensagem(TipoOperacao tipoOperacao, BigDecimal valor, LocalDateTime efetuadaEm, UUID idCliente, String numeroDaConta) {
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
        this.efetuadaEm = efetuadaEm;
        this.idCliente = idCliente;
        this.numeroDaConta = numeroDaConta;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetuadaEm() {
        return efetuadaEm;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public Transacao toModel() {
        return new Transacao(tipoOperacao, valor, efetuadaEm, idCliente, numeroDaConta);
    }
}
