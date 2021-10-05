package br.com.zup.edu.dojo.orquestrador.kakfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class KafkaTarefas {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private Logger logger = LoggerFactory.getLogger(KafkaTarefas.class);

    @Transactional
    @Scheduled(fixedDelayString = "${kafka.fixed-delay}", initialDelayString = "${kafka.intial-delay}")
    public void enviaMensagens() {
        List<Transacao> transacoes = transacaoRepository.findAll();

        if (transacoes.isEmpty()){
            logger.info("Nenhuma transacao kafka enviada.");
            return;
        }

        transacoes.forEach(
                transacao -> {
                    try {
                        kafkaProducerService.enviaTransacao(new TransacaoMensagem(transacao));
                        transacaoRepository.delete(transacao);
                        logger.info("Mensagem enviada via kafka: " + transacao.getId());
                    } catch (Exception e) {
                        logger.error("Não foi possivel enviar a mensagem via kafka", e);
                    }
                }
        );
    }
}
