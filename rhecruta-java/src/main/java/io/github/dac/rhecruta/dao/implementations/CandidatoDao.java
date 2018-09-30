package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.CandidatoDaoInterface;
import io.github.dac.rhecruta.models.Candidato;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CandidatoDao implements CandidatoDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public CandidatoDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public List<Candidato> listarTodos() {
        return entityManager.createQuery("FROM Candidato c").getResultList();
    }

    @Override
    public void salvar(Candidato candidato) {
        entityManager.persist(candidato);
    }

    @Override
    public void remover(Candidato candidato) {
        Candidato candidatoToRemove = entityManager.find(Candidato.class, candidato.getCpf());
        entityManager.remove(candidatoToRemove);
    }

    @Override
    public void atualizar(Candidato candidato) {
        entityManager.merge(candidato);
    }

    @Override
    public Candidato candidatoComCPF(String cpf) {
        return (Candidato) entityManager
                .createQuery("FROM Candidato c WHERE c.Cpf = :cpf")
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    @Override
    public Candidato candidatoComEmail(String email) {
        return (Candidato) entityManager
                .createQuery("FROM Candidato c where c.Email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public Boolean login(String email, String password) {
        return entityManager
                .createQuery("FROM Candidadto c where c.Email = :email AND c.Senha = :senha")
                .setParameter("email", email)
                .setParameter("senha", password)
                .getSingleResult() == null;
    }
}
