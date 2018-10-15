package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.javamail.NewsweekController;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.service.CandidatoService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CandidatoController implements Serializable {

    @EJB
    private CandidatoService candidatoService;

    @EJB
    private NewsweekController newsweekController;

    private Candidato candidato;

    public void adicionarInteresse(Integer idVaga) {

        if (candidato.getInteresses().contains(idVaga))
            return;

        this.candidato.adicionarInteresse(idVaga);
        this.candidatoService.atualizar(candidato);
        this.candidato = this.candidatoService.candidatoComEmail(this.candidato.getEmail());
    }

    public void removerInteresse(Integer idVaga) {

        if (!candidato.getInteresses().contains(idVaga))
            return;

        this.candidato.removerInteresse(idVaga);
        this.candidatoService.atualizar(candidato);
        this.candidato = this.candidatoService.candidatoComEmail(this.candidato.getEmail());
    }
    
    
    public Boolean login(String email, String senha) {
        if (this.candidatoService.login(email, senha)) {
            this.candidato = this.candidatoService.candidatoComEmail(email);
            return true;
        } else {

            return false;
        }

    }

    public void registarNaNewsweek() {
        this.newsweekController.cadastrarEmail(this.candidato.getEmail());
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
