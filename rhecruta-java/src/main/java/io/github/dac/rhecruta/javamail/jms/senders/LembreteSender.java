package io.github.dac.rhecruta.javamail.jms.senders;

import io.github.dac.rhecruta.models.Entrevista;
import io.github.dac.rhecruta.service.EntrevistaService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@JMSDestinationDefinition(
        interfaceName = "javax.jms.Topic",
        name = "java:global/jms/notificacao",
        resourceAdapter = "jmsra"
)
public class LembreteSender {

    @Resource(lookup = "java:global/jms/notificacao")
    private Topic topic;

    @Inject
    private JMSContext context;

    @EJB
    private EntrevistaService entrevistaService;

    @Schedule(dayOfWeek = "*", hour = "0", minute = "0", persistent = true)
    public void sendLembretes() {

        List<Entrevista> entrevistas = this.entrevistaService.recuperarTodasEntrevistas();

        LocalDate hoje = LocalDate.now();

        List<Entrevista> entrevistasDoDia = entrevistas
                .stream()
                .filter(entrevista -> entrevista.getDiaDaEntrevista().isEqual(hoje))
                .collect(Collectors.toList());

        entrevistasDoDia.forEach(entrevista -> sendEmail(entrevista));
    }

    // TODO agrupar todas as entrevistas no msm email
    private void sendEmail(Entrevista entrevista) {

        JMSProducer producer = this.context.createProducer();

        String tituloEmail = "Rhecruta - Você tem entrevista (s) marcada (s) para hoje.";

        String corpoEmail = new StringBuilder()
                .append("Olá, ")
                .append(entrevista.getCandidatura().getCandidato().getNome())
                .append(" você tem uma entrevista marcada para hoje às ")
                .append(entrevista.getHorarioDaEntrevista())
                .append(".\n\n")
                .append("Não se atrase! xD")
                .toString();


        JsonObject jsonEmail = Json
                .createObjectBuilder()
                .add("destinatario", entrevista.getCandidatura().getCandidato().getEmail())
                .add("titulo", tituloEmail)
                .add("corpo", corpoEmail)
                .build();

        try {
            Message message = context.createTextMessage(jsonEmail.toString());
            message.setStringProperty("typeMessage", "lembrete");
            producer.send(topic, message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }

    }

}
