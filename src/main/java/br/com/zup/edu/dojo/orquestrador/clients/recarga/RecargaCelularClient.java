package br.com.zup.edu.dojo.orquestrador.clients.recarga;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "recargaCelularClient", url = "${client.recargacelular.url}")
public interface RecargaCelularClient {

    @GetMapping
    void verificaStatusOperadora();

    @PostMapping
    void solicitaRecargaOperadora(@RequestBody RecargaOperadoraRequest request);
}
