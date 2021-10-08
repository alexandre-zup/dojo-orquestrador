package br.com.zup.edu.dojo.orquestrador.kakfa;

import br.com.zup.edu.dojo.orquestrador.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoMensagem {
    private TipoTransacao tipoTransacao;
    private BigDecimal valor;
    private LocalDateTime efetuadaEm;
    private UUID idCliente;
    private String numeroDaConta;

    @Deprecated
    public TransacaoMensagem() {
    }

    public TransacaoMensagem(TipoTransacao tipoTransacao, BigDecimal valor, LocalDateTime efetuadaEm, UUID idCliente, String numeroDaConta) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.efetuadaEm = efetuadaEm;
        this.idCliente = idCliente;
        this.numeroDaConta = numeroDaConta;
    }

    public TransacaoMensagem(Transacao transacao) {
        this.tipoTransacao = transacao.getOperacao();
        this.valor = transacao.getValor();
        this.efetuadaEm = transacao.getEfetuadaEm();
        this.idCliente = transacao.getIdCliente();
        this.numeroDaConta = transacao.getNumeroDaConta();
    }

    public TipoTransacao getTipoOperacao() {
        return tipoTransacao;
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
        return new Transacao(tipoTransacao, valor, efetuadaEm, idCliente, numeroDaConta);
    }
}
