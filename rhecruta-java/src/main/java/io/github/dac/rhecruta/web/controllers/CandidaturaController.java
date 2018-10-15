package io.github.dac.rhecruta.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.enums.ParecerEnum;
import io.github.dac.rhecruta.javamail.jms.senders.NotificacaoSender;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Vaga;
import io.github.dac.rhecruta.service.CandidaturaService;
import io.github.dac.rhecruta.service.VagaService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.json.JsonObject;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CandidaturaController implements Serializable {

    @EJB
    private CandidaturaService candidaturaService;

    @EJB
    private VagaService vagaService;

    @Inject
    private CandidatoController candidatoController;

    @EJB
    private NotificacaoSender notificacaoSender;

    private Candidatura candidatura;

    private Vaga vaga;

    private String parecer;

    @PostConstruct
    public void init() {
        this.vaga = new Vaga();
    }

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

    public String verMais(Integer idCandidatura) {
        this.candidatura = this.candidaturaService.candidaturaComId(idCandidatura);
        this.vaga = convertToVaga(this.vagaService.recuperarVagaComId(candidatura.getVagaId()));
        return "candidatura.xhtml";
    }

    public void removerCurriculo() {
        this.candidatura.setCurriculoCandidato(null);
        this.candidaturaService.atualizar(this.candidatura);
    }

    public String iniciarAdicionarCurriculo() {
        return "confirmarCurriculo.xhtml";
    }

    public String finalizarAdicionarCurriculo() {
        this.candidaturaService.atualizar(this.candidatura);
        return verMais(candidatura.getId());
    }

    public String iniciarAvaliacao(Integer idCandidatura) {
        this.candidatura = this.candidaturaService.candidaturaComId(idCandidatura);
        this.vaga = convertToVaga(this.vagaService.recuperarVagaComId(candidatura.getVagaId()));
        return "avaliarCandidatura.xhtml";
    }

    public String finalizarAvaliacao() {

        switch (parecer.toLowerCase()) {
            case "classificado":
                this.candidatura.setParecer(ParecerEnum.CLASSIFICADO);
                this.candidaturaService.atualizar(this.candidatura);
                this.notificacaoSender.notificarFinalizacaoRecrutamento(this.candidatura);
                break;

            case "reprovado":
                this.candidatura.setParecer(ParecerEnum.REPROVADO);
                this.candidaturaService.atualizar(this.candidatura);
                this.notificacaoSender.notificarFinalizacaoRecrutamento(this.candidatura);
                break;
        }

        return "todasCandidaturasGerente.xhtml";
    }

    public List<Candidatura> listarCandidaturas() {
        Candidato candidato = this.candidatoController.getCandidato();
        return this.candidaturaService.candidaturasPorCandidato(candidato);
    }

    public List<Candidatura> listarTodasCandidaturas() {
        return this.candidaturaService.listarTodas();
    }

    public String excluir(Integer idCandidatura) {
        Candidatura candidatura = this.candidaturaService.candidaturaComId(idCandidatura);
        this.candidaturaService.remover(candidatura);

        return null;
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

    public Candidatura getCandidatura() {
        return candidatura;
    }

    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }
}
