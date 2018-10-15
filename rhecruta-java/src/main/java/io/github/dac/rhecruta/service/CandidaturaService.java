package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.CandidaturaDaoInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Local
@Stateless
public class CandidaturaService {

    @EJB
    private CandidaturaDaoInterface candidaturaDao;

    @EJB
    private EntrevistaService entrevistaService;

    public Integer salvar(Candidatura candidatura) {
        candidaturaDao.salvar(candidatura);
        return candidatura.getId();
    }

    public void remover(Candidatura candidatura) {
        try {
            this.entrevistaService.remover(
                    this.entrevistaService.entrevistaDaCandidatura(candidatura)
            );

            this.candidaturaDao.remover(candidatura);
        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }
    }

    public void atualizar(Candidatura candidatura) {
        candidaturaDao.atualizar(candidatura);
    }

    public List<Candidatura> listarTodas() {
        try {
            return Collections.unmodifiableList(
                    candidaturaDao.listarTodas()
            );

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }

    public Candidatura candidaturaComId(Integer id) {
        try {
            return this.candidaturaDao.candidaturaComId(id);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public List<Candidatura> candidaturasPorCandidato(Candidato candidato) {
        try {
            return Collections.unmodifiableList(
                    candidaturaDao.candidaturasPorCandidato(candidato)
            );

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }
}
