package io.github.dac.rhecruta.service;

import io.github.dac.rhecruta.dao.interfaces.CandidatoDaoInterface;
import io.github.dac.rhecruta.models.Candidato;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class CandidatoService {

    @EJB
    private CandidatoDaoInterface candidatoDao;

    public List<Candidato> listarTodos() {
        return candidatoDao.listarTodos();
    }

    public void salvar(Candidato candidato) {
        candidatoDao.salvar(candidato);
    }

    public void remover(Candidato candidato) {
        candidatoDao.remover(candidato);
    }

    public void atualizar(Candidato candidato) {
        candidatoDao.atualizar(candidato);
    }

    public Candidato candidatoComCPF(String cpf) {
        return candidatoDao.candidatoComCPF(cpf);
    }

    public Candidato candidatoComEmail(String email) {
        return candidatoDao.candidatoComEmail(email);
    }

    public Boolean login(String email, String password) {
        return candidatoDao.login(email, password);
    }
}
