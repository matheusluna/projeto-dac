package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.CandidatoDaoInterface;
import io.github.dac.rhecruta.models.Candidato;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless(name = "CandidatoDao")
public class CandidatoDao implements CandidatoDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public CandidatoDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public List<Candidato> listarTodos() throws NoResultException {
        return entityManager.createQuery("FROM Candidato c").getResultList();
    }

    @Override
    public void salvar(Candidato candidato) {
        entityManager.persist(candidato);
    }

    @Override
    public void remover(Candidato candidato) throws NoResultException {
        Candidato candidatoToRemove = entityManager.find(Candidato.class, candidato.getCpf());
        entityManager.remove(candidatoToRemove);
    }

    @Override
    public void atualizar(Candidato candidato) {
        entityManager.merge(candidato);
    }

    @Override
    public Candidato candidatoComCPF(String cpf) throws NoResultException {
        return (Candidato) entityManager
                .createQuery("FROM Candidato c WHERE c.cpf = :cpf")
                .setParameter("cpf", cpf)
                .getSingleResult();

    }

    @Override
    public Candidato candidatoComEmail(String email) throws NoResultException {
        return (Candidato) entityManager
                .createQuery("FROM Candidato c where c.email = :email")
                .setParameter("email", email)
                .getSingleResult();

    }

    @Override
    public Boolean login(String email, String password) throws NoResultException {
        return entityManager
                .createQuery("FROM Candidato c where c.email = :email AND c.senha = :senha")
                .setParameter("email", email)
                .setParameter("senha", password)
                .getSingleResult() == null;

    }
}
