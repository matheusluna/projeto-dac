package io.github.dac.rhecruta.web.controllers;

import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;
import io.github.dac.rhecruta.service.CandidaturaService;
import io.github.dac.rhecruta.service.EntrevistaService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EntrevistaController implements Serializable {

    @EJB
    private EntrevistaService entrevistaService;

    @EJB
    private CandidaturaService candidaturaService;

    private Entrevista entrevista;

    private List<Entrevista> entrevistas;

    private String busca;
    private String tipoBusca;
    private Boolean aux;

    @PostConstruct
    public void init() {
        this.entrevista = new Entrevista();
        this.entrevistas = listarTodas();
        this.aux = false;
    }

    public void buscar() {

        aux = true;

        switch (tipoBusca.toLowerCase()) {
            case "candidato":
                this.entrevistas = this.entrevistaService.entrevistasPorCandidato(busca);
                break;
            case "empresa":
                this.entrevistas = this.entrevistaService.entrevistasPorNomeEmpresa(busca);
                break;
        }

    }

    public void limpar() {
        this.entrevistas = listarTodas();
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

    public String cancelarEntrevista(Entrevista entrevista) {
        this.entrevistaService.remover(entrevista);

        return "minhasEntrevistas.xhtml";
    }

    public String removerEntrevista(Entrevista entrevista) {
        this.entrevistaService.remover(entrevista);

        return "minhasEntrevistas.xhtml";
    }

    public String editar(Entrevista entrevista) {
        this.entrevista = entrevista;
        return "editarEntrevista.xhtml";
    }

    public String atualizarEntrevista() {
        this.entrevistaService.atualizar(entrevista);
        return "todasEntrevistas.xhtml";
    }

    public List<Entrevista> listar(String email) {
        return entrevistaService.entrevistasPorCandidato(email);
    }

    public List<Entrevista> listarTodas() {
        return entrevistaService.recuperarTodasEntrevistas();
    }

    public Entrevista getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Entrevista entrevista) {
        this.entrevista = entrevista;
    }

    public List<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(List<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }
}
