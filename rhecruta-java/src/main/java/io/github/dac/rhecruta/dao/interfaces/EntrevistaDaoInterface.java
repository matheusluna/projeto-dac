package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import javax.persistence.NoResultException;
import java.util.List;

public interface EntrevistaDaoInterface {

    public void salvar(Entrevista entrevista);
    public void remover(Entrevista entrevista) throws NoResultException;
    public void atualizar(Entrevista entrevista);
    public Entrevista entrevistaComId(Integer id) throws NoResultException;
    public List<Entrevista> entrevistasPorCandidato(String candidatoEmail) throws NoResultException;
    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura) throws NoResultException;
}
