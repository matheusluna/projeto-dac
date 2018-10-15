package io.github.dac.rhecruta.javamail.jms.senders;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Vaga;
import io.github.dac.rhecruta.service.VagaService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;

@Stateless
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Queue",
        name = "java:global/jms/email",
        resourceAdapter = "jmsra"
)
public class NotificacaoSender {

    @Resource(lookup = "java:global/jms/email")
    private Queue queue;

    @Inject
    private JMSContext context;

    @EJB
    private VagaService vagaService;

    public void notificarFinalizacaoRecrutamento(Candidatura candidaturaParameter) {

        //  Tornando Objeto Thread Safety
        Candidatura candidatura = candidaturaParameter.clone();
        Vaga vaga = convertToVaga(this.vagaService.recuperarVagaComId(candidatura.getVagaId()));

        JMSProducer producer = this.context.createProducer();

        String tituloEmail = "Rhecruta - Processo de Recrutamento Finalizado";

        String corpoEmail = new StringBuilder()
                .append("Olá ")
                .append(candidatura.getCandidato().getNome())
                .append(". ")
                .append("O processo de recrutamento para a vaga ")
                .append(vaga.getTitle())
                .append(" foi finalizado.")
                .append("\n\n\n")
                .append("O seu resultado é : ")
                .append(candidatura.getParecer())
                .append(".\n\n\n")
                .append("Obrigado por utiliza nosso sistema! xD")
                .toString();

        JsonObject jsonEmail = Json
                .createObjectBuilder()
                .add("destinatario", candidatura.getCandidato().getEmail())
                .add("titulo", tituloEmail)
                .add("corpo", corpoEmail)
                .build();

        Message message = context.createTextMessage(jsonEmail.toString());
        producer.send(queue, message);

    }

    private Vaga convertToVaga(JsonObject jsonObject) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(jsonObject.toString(), Vaga.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
