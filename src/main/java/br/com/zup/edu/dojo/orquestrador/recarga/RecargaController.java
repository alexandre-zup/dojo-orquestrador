package br.com.zup.edu.dojo.orquestrador.recarga;

import br.com.zup.edu.dojo.orquestrador.clients.recarga.RecargaCelularClient;
import br.com.zup.edu.dojo.orquestrador.kakfa.TipoTransacao;
import br.com.zup.edu.dojo.orquestrador.transacao.TransacaoContaDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/api/conta")
public class RecargaController {

    @Autowired
    private TransacaoContaDigitalService transacaoContaDigitalService;

    @Autowired
    private RecargaCelularClient client;


    @PostMapping("/{idCliente}/recarga")
    private ResponseEntity<?> solicitaRecarga(@PathVariable UUID idCliente, @Valid @RequestBody RecargaRequest request) {

        try {
            client.verificaStatusOperadora();
        } catch (Exception e) {
            ResponseEntity.status(503).build();
        }
        ResponseEntity<?> response = transacaoContaDigitalService.processaTransacao(idCliente, request.paraContaDigitalRequest(), TipoTransacao.RECARGA_CELULAR);
        if (response.getStatusCode() != HttpStatus.OK) return response;
        try {
            client.solicitaRecargaOperadora(request.paraRecargaOperadoraRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
