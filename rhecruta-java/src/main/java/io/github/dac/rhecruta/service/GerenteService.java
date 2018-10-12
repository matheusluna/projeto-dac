package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.GerenteDaoInterface;
import io.github.dac.rhecruta.models.Gerente;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Local
@Stateless
public class GerenteService {

    @EJB
    private GerenteDaoInterface gerenteDao;

    public String salvar(Gerente gerente) {
        this.gerenteDao.salvar(gerente);
        return gerente.getCpf();
    }

    public void atualizar(Gerente gerente) {
        this.gerenteDao.atualizar(gerente);
    }

    public List<Gerente> listarTodos() {
        return this.gerenteDao.listarTodos();
    }

    public void remover(Gerente gerente) {
        try {
            this.gerenteDao.remover(gerente);
        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }

    }
    public Gerente gerenteComCPF(String cpf) {

        try {
            return this.gerenteDao.gerenteComCPF(cpf);

        } catch (NoResultException ex) {
            return null;
        }

    }
    public Gerente gerenteComEmail(String email) {

        try {
            return this.gerenteDao.gerenteComEmail(email);

        } catch (NoResultException ex) {
            return null;
        }

    }
    public Boolean login(String email, String password) {

        try {
            return this.gerenteDao.login(email, password);

        } catch (NoResultException ex) {
            return false;
        }

    }


}
