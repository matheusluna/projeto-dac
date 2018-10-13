package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.models.Avaliador;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Gerente;
import io.github.dac.rhecruta.service.AvaliadorService;
import io.github.dac.rhecruta.service.CandidatoService;
import io.github.dac.rhecruta.service.GerenteService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Collections;

@Named
@RequestScoped
public class CadastroController {

    @EJB
    private AvaliadorService avaliadorService;

    @EJB
    private CandidatoService candidatoService;

    @EJB
    private GerenteService gerenteService;

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;

    public String cadastrar() {

        switch (tipoUsuario.toLowerCase()) {
            case "gerente":
                if (persistGerente())
                    return "index.xhtml";
                break;
            case "avaliador":
                if (persistAvaliador())
                    return "index.xhtml";
                break;
            case "candidato":
                if (persistCandidato())
                    return "index.xhtml";
                break;
            default:
                return "cadastro.xhtml?error=2";
        }

        return "cadastro.xhtml?error=1";
    }

    private Boolean persistGerente() {
        Gerente gerente = new Gerente(nome, email, senha, cpf);
        String gerenteCPF = this.gerenteService.salvar(gerente);

        if (gerenteCPF != null && gerenteCPF.length() == 11)
            return true;
        else
            return false;
    }

    private Boolean persistAvaliador() {
        Avaliador avaliador = new Avaliador(nome, email, senha, cpf);
        String avaliadorCPF = this.avaliadorService.salvar(avaliador);

        if (avaliadorCPF != null && avaliadorCPF.length() == 11)
            return true;
        else
            return false;
    }

    private Boolean persistCandidato() {
        Candidato candidato = new Candidato(nome, email, senha, cpf, Collections.emptyList());
        String candidatoCPF = this.candidatoService.salvar(candidato);

        if (candidatoCPF != null && candidatoCPF.length() == 11)
            return true;
        else
            return false;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
