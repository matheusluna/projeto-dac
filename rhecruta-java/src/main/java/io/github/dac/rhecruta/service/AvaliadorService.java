package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.AvaliadorDaoInterface;
import io.github.dac.rhecruta.models.Avaliador;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AvaliadorService {

    @EJB
    private AvaliadorDaoInterface avaliadorDao;

    public List<Avaliador> listarTodos() {
        return avaliadorDao.listarTodos();
    }

    public void salvar(Avaliador avaliador) {
        avaliadorDao.salvar(avaliador);
    }

    public void remover(Avaliador avaliador) {
        avaliadorDao.remover(avaliador);
    }

    public void atualizar(Avaliador avaliador) {
        avaliadorDao.atualizar(avaliador);
    }

    public Avaliador avaliadorComCPF(String cpf) {
        return avaliadorDao.avaliadorComCPF(cpf);
    }

    public Avaliador avaliadorComEmail(String email) {
        return avaliadorDao.avaliadorComEmail(email);
    }

    public Boolean login(String email, String password) {
        return avaliadorDao.login(email, password);
    }
}
