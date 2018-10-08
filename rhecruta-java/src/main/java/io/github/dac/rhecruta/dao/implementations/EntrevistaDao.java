package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.EntrevistaDaoInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Local
@Stateless(name = "EntrevistaDao")
public class EntrevistaDao implements EntrevistaDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public EntrevistaDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public void salvar(Entrevista entrevista) {
        entityManager.persist(entrevista);
    }

    @Override
    public void remover(Entrevista entrevista) {
        try {
            Entrevista entrevistaToRemove = entityManager.find(Entrevista.class, entrevista.getId());
            entityManager.remove(entrevistaToRemove);

        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }
    }

    @Override
    public void atualizar(Entrevista entrevista) {
        entityManager.merge(entrevista);
    }

    @Override
    public Entrevista entrevistaComId(Integer id) {
        try {
            return entityManager.find(Entrevista.class, id);

        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public List<Entrevista> entrevistasPorCandidato(String candidatoEmail) {
        try {
            return entityManager
                    .createQuery("FROM Entrevista e WHERE e.candidatura.candidato.email = :candidatoEmail")
                    .setParameter("candidatoEmail", candidatoEmail)
                    .getResultList();

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }


    }

    @Override
    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura) {
        try {
            return entityManager
                    .createQuery("FROM Entrevista e WHERE e.Candidatura.id = :candidaturaId")
                    .setParameter("candidaturaId", candidatura.getId())
                    .getResultList();

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }
}
