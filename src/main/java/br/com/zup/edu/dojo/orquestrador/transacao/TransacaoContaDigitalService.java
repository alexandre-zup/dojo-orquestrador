package br.com.zup.edu.dojo.orquestrador.transacao;

import br.com.zup.edu.dojo.orquestrador.clients.contadigital.ContaDigitalClient;
import br.com.zup.edu.dojo.orquestrador.clients.contadigital.TransacaoContaDigitalRequest;
import br.com.zup.edu.dojo.orquestrador.handler.ValidationErrorsOutputDto;
import br.com.zup.edu.dojo.orquestrador.kakfa.KafkaProducerService;
import br.com.zup.edu.dojo.orquestrador.kakfa.TipoTransacao;
import br.com.zup.edu.dojo.orquestrador.kakfa.TransacaoMensagem;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransacaoContaDigitalService {

    @Autowired
    private ContaDigitalClient contaDigitalClient;

    @Autowired
    private KafkaProducerService kafkaService;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public ResponseEntity<?> processaTransacao(UUID idCliente, NovaTransacaoContaDigitalRequest request, TipoTransacao tipoTransacao) {
        TransacaoContaDigitalRequest transacaoRequest = request.paraTransacao(tipoTransacao.getTransacaoContaDigital());

        try {
            contaDigitalClient.criaTransacao(idCliente, transacaoRequest);
        } catch (FeignException.BadRequest e) {
            return ResponseEntity.badRequest().body(e.contentUTF8());
        } catch (FeignException.UnprocessableEntity e) {
            return ResponseEntity.unprocessableEntity().body(e.contentUTF8());
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.contentUTF8());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ValidationErrorsOutputDto("Desculpe, tente novamente mais tarde."));
        }

        TransacaoMensagem mensagem = request
                .paraTransacaoMensagem(idCliente, tipoTransacao);

        try {
            kafkaService.enviaTransacao(mensagem);
        } catch (Exception e) {
            transacaoRepository.save(mensagem.toModel());
        }

        return ResponseEntity.ok().build();
    }
}
