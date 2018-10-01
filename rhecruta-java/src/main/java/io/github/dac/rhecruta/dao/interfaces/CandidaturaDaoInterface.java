package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidato;
import io.github.dac.rhecruta.models.Candidatura;

import java.util.List;

public interface CandidaturaDaoInterface {

    public void salvar(Candidatura candidatura);
    public void remover(Candidatura candidatura);
    public void atualizar(Candidatura candidatura);
    public List<Candidatura> candidaturasPorCandidato(Candidato candidato);
}
