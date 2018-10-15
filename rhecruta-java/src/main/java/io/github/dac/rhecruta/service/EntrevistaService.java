package io.github.dac.rhecruta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dac.rhecruta.dao.interfaces.EntrevistaDaoInterface;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;
import io.github.dac.rhecruta.models.Vaga;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Local
@Stateless
public class EntrevistaService {

    @EJB
    private EntrevistaDaoInterface entrevistaDao;

    @EJB
    private VagaService vagaService;

    public Integer salvar(Entrevista entrevista) {
        entrevistaDao.salvar(entrevista);
        return entrevista.getId();
    }

    public void remover(Entrevista entrevista) {
        try {
            entrevistaDao.remover(entrevista);

        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }
    }

    public void atualizar(Entrevista entrevista) {
        entrevistaDao.atualizar(entrevista);
    }

    public Entrevista entrevistaComId(Integer id) {
        try {
            return this.entrevistaDao.entrevistaComId(id);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public List<Entrevista> recuperarTodasEntrevistas() {
        try {
            return Collections.unmodifiableList(this.entrevistaDao.recuperarTodasEntrevistas());

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }

    public Entrevista entrevistaDaCandidatura(Candidatura candidatura) {
        try {
            return this.entrevistaDao.entrevistaDaCandidatura(candidatura);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public List<Entrevista> entrevistasPorCandidato(String candidatoEmail) {
        try {
            return Collections.unmodifiableList(
                    this.entrevistaDao.entrevistasPorCandidato(candidatoEmail)
            );

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }

    public List<Entrevista> entrevistasPorNomeEmpresa(String nomeEmpresa) {

        List<Vaga> vagas = convertToList(vagaService.vagasPorEmpresa(nomeEmpresa));
        List<Integer> vagasId = new ArrayList<>();

        vagas.forEach(vaga -> vagasId.add(vaga.getId()));

        try {

            List<Entrevista> todasEntrevistas = this.entrevistaDao.recuperarTodasEntrevistas();

            List<Entrevista> entrevistasFiltradas = todasEntrevistas
                    .stream()
                    .filter(entrevista -> vagasId
                            .contains(
                                    entrevista.getCandidatura().getVagaId()
                            )
                    ).collect(Collectors.toList());

            return Collections.unmodifiableList(
                    entrevistasFiltradas
            );

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

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

}
