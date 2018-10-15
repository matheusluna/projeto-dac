package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import javax.persistence.NoResultException;
import java.util.List;

public interface CandidaturaDaoInterface {


    public void salvar(Candidatura candidatura);
    public void atualizar(Candidatura candidatura);
    public List<Candidatura> listarTodas() throws NoResultException;
    public void remover(Candidatura candidatura) throws NoResultException;
    public Candidatura candidaturaComId(Integer id) throws NoResultException;
    public List<Candidatura> candidaturasPorCandidato(Candidato candidato) throws NoResultException;
}
