package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.EntrevistaDaoInterface;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class EntrevistaService {

    @EJB
    private EntrevistaDaoInterface entrevistaDao;

    public Integer salvar(Entrevista entrevista) {
        entrevistaDao.salvar(entrevista);
        return entrevista.getId();
    }

    public void remover(Entrevista entrevista) {
        entrevistaDao.remover(entrevista);
    }

    public void atualizar(Entrevista entrevista) {
        entrevistaDao.atualizar(entrevista);
    }

    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura) {
        return entrevistaDao.entrevistasPorCandidatura(candidatura);
    }
}
