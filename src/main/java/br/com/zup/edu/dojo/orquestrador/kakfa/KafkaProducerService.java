package br.com.zup.edu.dojo.orquestrador.kakfa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topico.transacoes}")
    private String topicoTransacoes;

    public void enviaTransacao(TransacaoMensagem mensagem) {
        this.kafkaTemplate.send(topicoTransacoes, mensagem);
    }
}
