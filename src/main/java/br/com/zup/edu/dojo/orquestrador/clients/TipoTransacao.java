package br.com.zup.edu.dojo.orquestrador.clients;

import br.com.zup.edu.dojo.orquestrador.kakfa.TipoOperacao;

public enum TipoTransacao {
    DEBITO(TipoOperacao.SAQUE),
    CREDITO(TipoOperacao.DEPOSITO);

    private final TipoOperacao operacao;

    TipoTransacao(TipoOperacao operacao) {
        this.operacao = operacao;
    }

    public TipoOperacao getOperacao() {
        return operacao;
    }
}
