package io.github.dac.rhecruta.web.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class LoginController {

    @Inject
    private AvaliadorController avaliadorController;

    @Inject
    private CandidatoController candidatoController;

    @Inject
    private GerenteController gerenteController;

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
                if (this.gerenteController.login(email, senha))
                    return "homeGerente.xhtml";
                else return "index.xhtml?error=1";
            case "avaliador":
                if (this.avaliadorController.login(email, senha))
                    return "homeAvaliador.xhtml";
                else return "index.xhtml?error=1";
            case "candidato":
                if (this.candidatoController.login(email, senha))
                    return "homeCandidato.xhtml";
                else return "index.xhtml?error=1";
            default:
                return "index.xhtml?error=2";
        }
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
