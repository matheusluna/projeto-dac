package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.CandidaturaDaoInterface;
import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless(name = "CadidaturaDao")
public class CandidaturaDao implements CandidaturaDaoInterface {

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
    public void atualizar(Candidatura candidatura) {
        entityManager.merge(candidatura);
    }

    @Override
    public void remover(Candidatura candidatura) throws NoResultException {
        Candidatura candidaturaToRemove = entityManager.find(Candidatura.class, candidatura.getId());
        entityManager.remove(candidaturaToRemove);
    }

    @Override
    public List<Candidatura> listarTodas() throws NoResultException {
        return entityManager
                .createQuery("FROM Candidatura c")
                .getResultList();
    }

    @Override
    public Candidatura candidaturaComId(Integer id) throws NoResultException {
        return entityManager.find(Candidatura.class, id);
    }

    @Override
    public List<Candidatura> candidaturasPorCandidato(Candidato candidato) throws NoResultException {
        return entityManager
                .createQuery("FROM Candidatura c WHERE c.candidato.cpf = :candidatoCpf")
                .setParameter("candidatoCpf", candidato.getCpf())
                .getResultList();

    }

}
