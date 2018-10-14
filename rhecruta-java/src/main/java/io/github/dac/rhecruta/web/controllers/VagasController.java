package io.github.dac.rhecruta.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.models.Vaga;
import io.github.dac.rhecruta.service.VagaService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named
@RequestScoped
public class VagasController {

    @EJB
    private VagaService vagaService;

    private String busca;
    private String tipoBusca;

    private List<Vaga> vagas;

    @PostConstruct
    public void init() {
        this.vagas = new ArrayList<>();
    }

    public void buscar() {
        switch (tipoBusca.toLowerCase()) {
            case "cidade":
                this.vagas = convertToList(this.vagaService.vagasPorCidade(busca));
                break;

            case "empresa":
                this.vagas = convertToList(this.vagaService.vagasPorEmpresa(busca));
                break;

            case "descricao":
                this.vagas = convertToList(this.vagaService.vagasComDescricao(busca));
                break;
        }
    }

    public String tituloDaVagaComId(Integer idVaga) {
        return this.vagaService.recuperarVagaComId(idVaga).getString("title");
    }

    private List<Vaga> convertToList(JsonArray jsonArray) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return Arrays.asList(mapper.readValue(jsonArray.toString(), Vaga[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

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

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }
}
