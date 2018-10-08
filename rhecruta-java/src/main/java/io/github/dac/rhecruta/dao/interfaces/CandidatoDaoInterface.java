package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidato;

import javax.persistence.NoResultException;
import java.util.List;

public interface CandidatoDaoInterface {

    public List<Candidato> listarTodos() throws NoResultException;
    public void salvar(Candidato candidato);
    public void remover(Candidato candidato) throws NoResultException;
    public void atualizar(Candidato candidato);
    public Candidato candidatoComCPF(String cpf) throws NoResultException;
    public Candidato candidatoComEmail(String email) throws NoResultException;
    public Boolean login(String email, String password) throws NoResultException;
}
