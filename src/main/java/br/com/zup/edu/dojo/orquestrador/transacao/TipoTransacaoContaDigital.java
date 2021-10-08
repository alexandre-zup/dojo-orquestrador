package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.kakfa.TipoTransacao;

public enum TipoTransacaoContaDigital {
    DEBITO(TipoTransacao.SAQUE),
    CREDITO(TipoTransacao.DEPOSITO);


    private final TipoTransacao operacao;

    TipoTransacaoContaDigital(TipoTransacao operacao) {
        this.operacao = operacao;
    }

}
