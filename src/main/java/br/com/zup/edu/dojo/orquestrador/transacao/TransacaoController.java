package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.kakfa.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/conta")
public class TransacaoController {

    @Autowired
    private TransacaoContaDigitalService transacaoContaDigitalService;

    @Transactional
    @PostMapping(value = "/{idCliente}/deposito", produces = {"application/json"})
    public ResponseEntity<?> deposita(@PathVariable UUID idCliente,
                                      @RequestBody @Valid NovaTransacaoContaDigitalRequest request) {

        return transacaoContaDigitalService.processaTransacao(idCliente, request, TipoTransacao.DEPOSITO);
    }

    @Transactional
    @PostMapping(value = "/{idCliente}/saque", produces = {"application/json"})
    public ResponseEntity<?> saque(@PathVariable UUID idCliente,
                                   @RequestBody @Valid NovaTransacaoContaDigitalRequest request) {

        return transacaoContaDigitalService.processaTransacao(idCliente, request, TipoTransacao.SAQUE);
    }
}
