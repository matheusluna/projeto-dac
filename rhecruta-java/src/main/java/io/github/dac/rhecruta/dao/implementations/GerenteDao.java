package io.github.dac.rhecruta.dao.implementations;

import io.github.dac.rhecruta.dao.interfaces.GerenteDaoInterface;
import io.github.dac.rhecruta.models.Gerente;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless(name = "GerenteDao")
public class GerenteDao implements GerenteDaoInterface {

    @PersistenceContext
    private final EntityManager entityManager;

    public GerenteDao() {
        this.entityManager = Persistence.createEntityManagerFactory("rhecruta").createEntityManager();
    }

    @Override
    public void salvar(Gerente gerente) {
        entityManager.persist(gerente);
    }

    @Override
    public void remover(Gerente gerente) throws NoResultException {
        Gerente gerenteToRemove = entityManager.find(Gerente.class, gerente.getCpf());
        entityManager.remove(gerenteToRemove);
    }

    @Override
    public void atualizar(Gerente gerente) {
        entityManager.merge(gerente);
    }

    @Override
    public List<Gerente> listarTodos() throws NoResultException {
        return entityManager.createQuery("FROM Gerente a").getResultList();
    }

    @Override
    public Gerente gerenteComCPF(String cpf) throws NoResultException {
        return (Gerente) entityManager
                .createQuery("FROM Gerente g WHERE g.cpf = :cpf")
                .setParameter("cpf", cpf)
                .getSingleResult();

    }

    @Override
    public Gerente gerenteComEmail(String email) throws NoResultException {
        return (Gerente) entityManager
                .createQuery("FROM Gerente g WHERE g.email = :email")
                .setParameter("email", email)
                .getSingleResult();

    }

    @Override
    public Boolean login(String email, String password) throws NoResultException {
        return entityManager
                .createQuery("FROM Gerente a WHERE a.email = :email AND a.senha = :senha")
                .setParameter("email", email)
                .setParameter("senha", password)
                .getSingleResult() != null;

    }
}
