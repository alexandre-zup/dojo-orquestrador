package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.clients.TransacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(value = "contaDigitalClient", url = "${client.contadigital.url}")
public interface ContaDigitalClient {
    @PostMapping("/{idCliente}/transacao")
    public void criaTransacao(@PathVariable UUID idCliente, @RequestBody TransacaoRequest request);
}
