package io.github.dac.rhecruta.web.controllers;

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

    private Candidato candidato;

    public void adicionarInteresse(Integer idVaga) {
        this.candidato.adicionarInteresse(idVaga);
        this.candidatoService.atualizar(candidato);
    }

    public Boolean login(String email, String senha) {
        if (this.candidatoService.login(email, senha)) {

            this.candidato = this.candidatoService.candidatoComEmail(email);
            return true;
        } else {

            return false;
        }

    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
