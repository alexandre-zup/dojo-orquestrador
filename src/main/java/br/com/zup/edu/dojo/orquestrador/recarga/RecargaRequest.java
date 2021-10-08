package br.com.zup.edu.dojo.orquestrador.recarga;

import br.com.zup.edu.dojo.orquestrador.clients.recarga.Operadora;
import br.com.zup.edu.dojo.orquestrador.clients.recarga.RecargaOperadoraRequest;
import br.com.zup.edu.dojo.orquestrador.transacao.NovaTransacaoContaDigitalRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RecargaRequest {

    @JsonProperty
    private String numeroCelular;
    @JsonProperty
    private Operadora operadora;
    @JsonProperty
    private Integer valor;
    @JsonProperty
    private String numeroConta;

    public NovaTransacaoContaDigitalRequest paraContaDigitalRequest(){
        return new NovaTransacaoContaDigitalRequest(this.numeroConta,new BigDecimal(this.valor));
    }

    public RecargaOperadoraRequest paraRecargaOperadoraRequest(){
        return new RecargaOperadoraRequest(this.numeroCelular,this.operadora, this.valor);
    }
}
