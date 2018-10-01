package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.CandidaturaDaoInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless(name = "CadidaturaDao")
public class CandidaturaDaoDao implements CandidaturaDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public CandidaturaDaoDao() {
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
