package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.CandidaturaInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CandidaturaDao implements CandidaturaInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public CandidaturaDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public void salvar(Candidatura candidatura) {
        entityManager.persist(candidatura);
    }

    @Override
    public void remover(Candidatura candidatura) {
        Candidatura candidaturaToRemove = entityManager.find(Candidatura.class, candidatura.getId());
        entityManager.remove(candidaturaToRemove);
    }

    @Override
    public void atualizar(Candidatura candidatura) {
        entityManager.merge(candidatura);
    }

    @Override
    public List<Candidatura> candidaturasPorCandidato(Candidato candidato) {
        return entityManager
                .createQuery("FROM Candidatura c WHERE c.Candidato.Cpf = :candidatoCpf")
                .setParameter("candidatoCpf", candidato.getCpf())
                .getResultList();
    }
}
