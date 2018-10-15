package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.models.Avaliador;
import io.github.dac.rhecruta.service.AvaliadorService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AvaliadorController implements Serializable {

    @EJB
    private AvaliadorService avaliadorService;

    private Avaliador avaliador;

    @PostConstruct
    public void init() {
        this.avaliador = new Avaliador();
    }

    public Boolean login(String email, String senha) {
        if (this.avaliadorService.login(email, senha)) {
            this.avaliador = this.avaliadorService.avaliadorComEmail(email);
            return true;
        } else {

            return false;
        }

    }

    public Avaliador getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }
}
