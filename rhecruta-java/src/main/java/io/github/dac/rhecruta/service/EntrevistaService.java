package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.EntrevistaDaoInterface;
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
public class EntrevistaService {

    @EJB
    private EntrevistaDaoInterface entrevistaDao;

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

    public List<Entrevista> entrevistasPorCandidato(String candidatoEmail) {
        try {
            return this.entrevistaDao.entrevistasPorCandidato(candidatoEmail);

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }

    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura) {
        try {
            return entrevistaDao.entrevistasPorCandidatura(candidatura);

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }

}
