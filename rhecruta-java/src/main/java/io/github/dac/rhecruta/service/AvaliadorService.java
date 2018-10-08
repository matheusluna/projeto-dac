package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.AvaliadorDaoInterface;
import io.github.dac.rhecruta.models.Avaliador;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Local
@Stateless
public class AvaliadorService {

    @EJB
    private AvaliadorDaoInterface avaliadorDao;

    public List<Avaliador> listarTodos() {
        return avaliadorDao.listarTodos();
    }

    public String salvar(Avaliador avaliador) {
        avaliadorDao.salvar(avaliador);
        return avaliador.getCpf();
    }

    public void remover(Avaliador avaliador) {
        try {
            avaliadorDao.remover(avaliador);
        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }
    }

    public void atualizar(Avaliador avaliador) {
        avaliadorDao.atualizar(avaliador);
    }

    public Avaliador avaliadorComCPF(String cpf) {
        try {
            return avaliadorDao.avaliadorComCPF(cpf);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public Avaliador avaliadorComEmail(String email) {
        try {
            return avaliadorDao.avaliadorComEmail(email);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public Boolean login(String email, String password) {
        try {
            return avaliadorDao.login(email, password);

        } catch (NoResultException ex) {
            return false;
        }

    }
}
