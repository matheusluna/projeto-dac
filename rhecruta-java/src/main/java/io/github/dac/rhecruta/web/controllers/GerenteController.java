package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.models.Gerente;
import io.github.dac.rhecruta.service.GerenteService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class GerenteController implements Serializable {

    @EJB
    private GerenteService gerenteService;

    private Gerente gerente;

    @PostConstruct
    public void init() {
        this.gerente = new Gerente();
    }

    public Boolean login(String email, String senha) {
        if (this.gerenteService.login(email, senha)) {
            this.gerente = this.gerenteService.gerenteComEmail(email);
            return true;
        } else {

            return false;
        }

    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
}
