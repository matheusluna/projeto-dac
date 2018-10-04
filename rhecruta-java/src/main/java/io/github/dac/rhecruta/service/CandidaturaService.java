package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.CandidaturaDaoInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class CandidaturaService {
    
    @EJB
    private CandidaturaDaoInterface candidaturaDao;

    public Integer salvar(Candidatura candidatura) {
        candidaturaDao.salvar(candidatura);
        return candidatura.getId();
    }

    public void remover(Candidatura candidatura) {
        candidaturaDao.remover(candidatura);
    }

    public void atualizar(Candidatura candidatura) {
        candidaturaDao.atualizar(candidatura);
    }

    public Candidatura candidaturaComId(Integer id) {
        return this.candidaturaDao.candidaturaComId(id);
    }

    public List<Candidatura> candidaturasPorCandidato(Candidato candidato) {
        return candidaturaDao.candidaturasPorCandidato(candidato);
    }
}
