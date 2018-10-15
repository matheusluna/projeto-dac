package io.github.dac.rhecruta.javamail;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.models.Vaga;
import io.github.dac.rhecruta.service.VagaService;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.json.JsonArray;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewsweekController {

    @EJB
    private VagaService vagaService;

    @EJB
    private EmailService emailService;

    private Integer ultimoIdVagaCadastrado;
    private List<String> emailCadastrados;

    @PostConstruct
    private void init() {
        this.emailCadastrados = new ArrayList<>();

        List<Vaga> vagas = convertToList(vagaService.recuperarTodasVagas());

        this.ultimoIdVagaCadastrado = vagas.get(vagas.size() -1).getId();
    }

    @Schedule(dayOfWeek="sun", hour="0", minute="0", persistent = true)
    public void sendNewsweek() {
        List<Vaga> updatedListVagas = convertToList(vagaService.recuperarTodasVagas());

        List<Vaga> novasVagas = updatedListVagas
                .stream()
                .filter(vaga -> vaga.getId() > ultimoIdVagaCadastrado)
                .collect(Collectors.toList());

        String tituloEmail = "NewsWeek Semanal - Site Rhecruta";
        String corpoEmail = generateCorpoEmail(novasVagas);

        recuperarEmails().forEach(email -> this.emailService.sendEmail(email, tituloEmail, corpoEmail));
    }

    @Lock(LockType.WRITE)
    public void cadastrarEmail(String email) {
        this.emailCadastrados.add(email);
    }

    @Lock(LockType.READ)
    public List<String> recuperarEmails() {
        return Collections.unmodifiableList(this.emailCadastrados);
    }

    private String generateCorpoEmail(List<Vaga> novasVagas) {

        StringBuilder stringBuilder = new StringBuilder();


        LocalDate localDate = LocalDate.now();
        stringBuilder
                .append("Olá, email se refere a NewsWeek do site Rhecruta referente ao dia ")
                .append(localDate.getDayOfMonth())
                .append(".");

        stringBuilder.append("\n\n");

        stringBuilder.append("ESTAS SÃO AS NOVAS VAGAS RESIGTRADAS NO NOSSO SISTEMA");


        stringBuilder.append("\n\n");


        novasVagas.forEach(vaga -> {
            stringBuilder.append("Título: ")
                    .append(vaga.getTitle())
                    .append("\n");

            stringBuilder
                    .append("Empresa: ")
                    .append(vaga.getCompany_name())
                    .append("\n");
            stringBuilder
                    .append("Local de Trabalho: ")
                    .append(vaga.getWorkplace())
                    .append("\n");

            stringBuilder
                    .append("Descricão: ")
                    .append(vaga.getDescription())
                    .append("\n");

            stringBuilder.append("\n");
        });

        return stringBuilder.toString();
    }

    private List<Vaga> convertToList(JsonArray jsonArray) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return Arrays.asList(mapper.readValue(jsonArray.toString(), Vaga[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

}
