package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.CandidatoDaoInterface;
import io.github.dac.rhecruta.models.Candidato;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

@Local
@Stateless
public class CandidatoService {

    @EJB
    private CandidatoDaoInterface candidatoDao;

    public List<Candidato> listarTodos() {
        return candidatoDao.listarTodos();
    }

    public String salvar(Candidato candidato) {
        candidatoDao.salvar(candidato);
        return candidato.getCpf();
    }

    public void remover(Candidato candidato) {
        try {
            candidatoDao.remover(candidato);

        } catch (NoResultException ex) {
//            ex.printStackTrace();
        }
    }

    public void atualizar(Candidato candidato) {
        candidatoDao.atualizar(candidato);
    }

    public Candidato candidatoComCPF(String cpf) {
        try {
            return candidatoDao.candidatoComCPF(cpf);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public Candidato candidatoComEmail(String email) {
        try {
            return candidatoDao.candidatoComEmail(email);

        } catch (NoResultException ex) {
            return null;
        }

    }

    public Boolean login(String email, String password) {
        try {
            return candidatoDao.login(email, password);

        } catch (NoResultException ex) {
            return false;
        }

    }
}
