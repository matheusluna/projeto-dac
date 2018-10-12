package io.github.dac.rhecruta.dao.interfaces;

import io.github.dac.rhecruta.models.Gerente;

import javax.persistence.NoResultException;
import java.util.List;

public interface GerenteDaoInterface {

    public void salvar(Gerente gerente);
    public void atualizar(Gerente gerente);
    public List<Gerente> listarTodos() throws NoResultException;
    public void remover(Gerente gerente) throws NoResultException;
    public Gerente gerenteComCPF(String cpf) throws NoResultException;
    public Gerente gerenteComEmail(String email) throws NoResultException;
    public Boolean login(String email, String password) throws NoResultException;
}
