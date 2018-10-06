package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.AvaliadorDaoInterface;
import io.github.dac.rhecruta.models.Avaliador;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Local
@Stateless(name = "AvaliadorDao")
public class AvaliadorDao implements AvaliadorDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public AvaliadorDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public List<Avaliador> listarTodos() {

        try {
            return entityManager.createQuery("FROM Avaliador a").getResultList();

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }

    }

    @Override
    public void salvar(Avaliador avaliador) {
        entityManager.persist(avaliador);
    }

    @Override
    public void remover(Avaliador avaliador) {
        Avaliador avaliadorToRemove = entityManager.find(Avaliador.class, avaliador.getCpf());

        if (avaliadorToRemove != null)
            entityManager.remove(avaliadorToRemove);
    }

    @Override
    public void atualizar(Avaliador avaliador) {
        entityManager.merge(avaliador);
    }

    @Override
    public Avaliador avaliadorComCPF(String cpf) {

        try {
            return (Avaliador) entityManager
                    .createQuery("FROM Avaliador a WHERE a.cpf = :cpf")
                    .setParameter("cpf", cpf)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Avaliador avaliadorComEmail(String email) {

        try {
            return (Avaliador) entityManager
                    .createQuery("FROM Avaliador a WHERE a.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public Boolean login(String email, String password) {

        try {
            return entityManager
                    .createQuery("FROM Avaliador a WHERE a.email = :email AND a.senha = :senha")
                    .setParameter("email", email)
                    .setParameter("senha", password)
                    .getSingleResult() == null;

        } catch (NoResultException ex) {
            return null;
        }

    }
}
