package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Avaliador;

import javax.persistence.NoResultException;
import java.util.List;

public interface AvaliadorDaoInterface {

    public List<Avaliador> listarTodos() throws NoResultException;
    public void salvar(Avaliador avaliador);
    public void remover(Avaliador avaliador) throws NoResultException;
    public void atualizar(Avaliador avaliador);
    public Avaliador avaliadorComCPF(String cpf) throws NoResultException;
    public Avaliador avaliadorComEmail(String email) throws NoResultException;
    public Boolean login(String email, String password) throws NoResultException;
}
