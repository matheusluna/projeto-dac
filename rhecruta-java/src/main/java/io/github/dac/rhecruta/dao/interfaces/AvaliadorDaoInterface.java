package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Avaliador;

import java.util.List;

public interface AvaliadorDaoInterface {

    public List<Avaliador> listarTodos();
    public void salvar(Avaliador avaliador);
    public void remover(Avaliador avaliador);
    public void atualizar(Avaliador avaliador);
    public Avaliador avaliadorComCPF(String cpf);
    public Avaliador avaliadorComEmail(String email);
    public Boolean login(String email, String password);
}
