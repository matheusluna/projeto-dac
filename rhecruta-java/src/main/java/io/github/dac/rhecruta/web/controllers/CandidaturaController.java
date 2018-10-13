package io.github.dac.rhecruta.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Vaga;
import io.github.dac.rhecruta.service.CandidaturaService;
import io.github.dac.rhecruta.service.VagaService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import java.io.IOException;

@Named
@RequestScoped
public class CandidaturaController {

    @EJB
    private CandidaturaService candidaturaService;

    @EJB
    private VagaService vagaService;

    @Inject
    private CandidatoController candidatoController;

    private Vaga vaga;

    public String iniciarCandidatura(Integer idVaga) {
        this.vaga = convertToVaga(vagaService.recuperarVagaComId(idVaga));
        return "confirmarCandidatura.xhtml";
    }

    public String finalizarCandidatura() {
        Candidatura candidatura = new Candidatura();
        candidatura.setVagaId(vaga.getId());
        candidatura.setCandidato(candidatoController.getCandidato());

        this.candidaturaService.salvar(candidatura);
        return "homeCandidato.xhtml";
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

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
