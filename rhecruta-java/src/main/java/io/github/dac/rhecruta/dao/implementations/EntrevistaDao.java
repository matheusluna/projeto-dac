package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.EntrevistaDaoInterface;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
        Entrevista entrevistaToRemove = entityManager.find(Entrevista.class, entrevista.getId());
        entityManager.remove(entrevistaToRemove);
    }

    @Override
    public void atualizar(Entrevista entrevista) {
        entityManager.merge(entrevista);
    }

    @Override
    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura) {
        return entityManager
                .createQuery("FROM Entrevista e WHERE e.Candidatura.Id = :candidaturaId")
                .setParameter("candidaturaId", candidatura.getId())
                .getResultList();
    }
}
