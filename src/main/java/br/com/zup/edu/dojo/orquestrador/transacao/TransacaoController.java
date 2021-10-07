package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.clients.TipoTransacao;
import br.com.zup.edu.dojo.orquestrador.clients.TransacaoRequest;
import br.com.zup.edu.dojo.orquestrador.handler.ValidationErrorsOutputDto;
import br.com.zup.edu.dojo.orquestrador.kakfa.KafkaProducerService;
import br.com.zup.edu.dojo.orquestrador.kakfa.TransacaoMensagem;
import br.com.zup.edu.dojo.orquestrador.kakfa.TransacaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/conta")
public class TransacaoController {
    @Autowired
    private ContaDigitalClient client;

    @Autowired
    private KafkaProducerService kafkaService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    @PostMapping(value = "/{idCliente}/deposito", produces = {"application/json"})
    public ResponseEntity<?> deposita(@PathVariable UUID idCliente,
                                      @RequestBody @Valid NovaTransacaoRequest request) {

        return processaTransacao(idCliente, request, TipoTransacao.CREDITO);
    }

    @Transactional
    @PostMapping(value = "/{idCliente}/saque", produces = {"application/json"})
    public ResponseEntity<?> saque(@PathVariable UUID idCliente,
                                   @RequestBody @Valid NovaTransacaoRequest request) {

        return processaTransacao(idCliente, request, TipoTransacao.DEBITO);
    }

    private ResponseEntity<?> processaTransacao(UUID idCliente, NovaTransacaoRequest request, TipoTransacao tipoTransacao) {
        TransacaoRequest transacaoRequest = request.paraTransacao(tipoTransacao);

        try {
            client.criaTransacao(idCliente, transacaoRequest);
        } catch (FeignException.BadRequest e) {
            return ResponseEntity.badRequest().body(e.contentUTF8());
        } catch (FeignException.UnprocessableEntity e) {
            return ResponseEntity.unprocessableEntity().body(e.contentUTF8());
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.contentUTF8());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ValidationErrorsOutputDto("Desculpe, tente novamente mais tarde."));
        }

        TransacaoMensagem mensagem = transacaoRequest
                .paraTransacaoMensagem(idCliente);

        try {
            kafkaService.enviaTransacao(mensagem);
        } catch (Exception e) {
            transacaoRepository.save(mensagem.toModel());
        }

        return ResponseEntity.ok().build();
    }
}
