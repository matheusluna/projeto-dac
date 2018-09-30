package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidatura;
import io.github.dac.rhecruta.models.Entrevista;

import java.util.List;

public interface EntrevistaDaoInterface {

    public void salvar(Entrevista entrevista);
    public void remover(Entrevista entrevista);
    public void atualizar(Entrevista entrevista);
    public List<Entrevista> entrevistasPorCandidatura(Candidatura candidatura);
}
