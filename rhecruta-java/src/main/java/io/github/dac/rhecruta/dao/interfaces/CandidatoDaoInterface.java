package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Candidato;

import java.util.List;

public interface CandidatoDaoInterface {

    public List<Candidato> listarTodos();
    public void salvar(Candidato candidato);
    public void remover(Candidato candidato);
    public void atualizar(Candidato candidato);
    public Candidato candidatoComCPF(String cpf);
    public Candidato candidatoComEmail(String email);
    public Boolean login(String email, String password);
}
