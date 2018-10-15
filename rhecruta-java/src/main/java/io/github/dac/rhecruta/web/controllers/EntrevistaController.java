package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;
import io.github.dac.rhecruta.service.CandidaturaService;
import io.github.dac.rhecruta.service.EntrevistaService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@SessionScoped
public class EntrevistaController implements Serializable {

    @EJB
    private EntrevistaService entrevistaService;

    @EJB
    private CandidaturaService candidaturaService;

    private Entrevista entrevista;

    @PostConstruct
    public void init() {
        this.entrevista = new Entrevista();
    }

    public String iniciarEntrevista(Integer idCandidatura) {
        this.entrevista = new Entrevista();

        Candidatura candidatura = candidaturaService.candidaturaComId(idCandidatura);

        this.entrevista.setCandidatura(candidatura);
        return "confirmarEntrevista.xhtml";
    }

    public String finalizarEntrevista() {
        this.entrevistaService.salvar(this.entrevista);

        return "minhasEntrevistas.xhtml";
    }
    
    public List<Entrevista> listar(String email){
        return entrevistaService.entrevistasPorCandidato(email);
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }
}
