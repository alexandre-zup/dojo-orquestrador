package br.com.zup.edu.dojo.orquestrador.kakfa;

import br.com.zup.edu.dojo.orquestrador.transacao.TipoTransacaoContaDigital;

public enum TipoTransacao {
    DEPOSITO{
        @Override
        public TipoTransacaoContaDigital getTransacaoContaDigital() {
            return TipoTransacaoContaDigital.CREDITO;
        }
    },
    SAQUE{
        @Override
        public TipoTransacaoContaDigital getTransacaoContaDigital() {
            return TipoTransacaoContaDigital.DEBITO;
        }
    },
    RECARGA_CELULAR{
        @Override
        public TipoTransacaoContaDigital getTransacaoContaDigital() {
            return TipoTransacaoContaDigital.DEBITO;
        }
    },
    BOLETO{
        @Override
        public TipoTransacaoContaDigital getTransacaoContaDigital() {
            return TipoTransacaoContaDigital.DEBITO;
        }
    };

    public abstract TipoTransacaoContaDigital getTransacaoContaDigital();

}
