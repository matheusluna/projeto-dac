package io.github.dac.rhecruta.web.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RedirectController {

    public String buscarVagas() {
        return "homeCandidato.xhtml";
    }

    public String minhasEntrevistas() {
        return "minhasEntrevistas.xhtml";
    }

    public String minhasCandidaturas() {
        return "minhasCandidaturas.xhtml";
    }

    public String candidatura() {
        return "confirmarCandidatura.xhtml";
    }

    public String meusInteresses() {
        return "meusInteresses.xhtml";
    }

    public String homeCandidato() {
        return "homeCandidato.xhtml";
    }

    public String todasEntrevistas() {
        return "todasEntrevistas.xhtml";
    }

    public String todasCandidaturas() {
        return "todasCandidaturas.xhtml";
    }

    public String homeAvaliador() {
        return "homeAvaliador.xhtml";
    }

    public String todasCandidaturasGerente() {
        return "todasCandidaturasGerente.xhtml";
    }


}
