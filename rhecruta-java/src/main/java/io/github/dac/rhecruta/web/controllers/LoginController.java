package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.service.AvaliadorService;
import io.github.dac.rhecruta.service.CandidatoService;
import io.github.dac.rhecruta.service.GerenteService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class LoginController {

    @EJB
    private AvaliadorService avaliadorService;

    @EJB
    private CandidatoService candidatoService;

    @EJB
    private GerenteService gerenteService;

    private String email;
    private String senha;
    private String tipoUsuario;
    private List<String> tiposUsuario;

    @PostConstruct
    public void init() {
        this.tiposUsuario = Arrays.asList("Gerente", "Avaliador", "Candidato");
    }

    public String login() {

        switch (tipoUsuario.toLowerCase()) {
            case "gerente":
                if (this.gerenteService.login(email, senha))
                    return "homeGerente.xhtml";
                break;
            case "avaliador":
                if (this.avaliadorService.login(email, senha))
                    return "homeAvaliador.xhtml";
                break;
            case "candidato":
                if (this.candidatoService.login(email, senha))
                    return "homeCandidato.xhtml";
                break;
            default:
                return "index.xhtml?error=2";
        }

        return "index.xhtml?error=1";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<String> tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }
}
