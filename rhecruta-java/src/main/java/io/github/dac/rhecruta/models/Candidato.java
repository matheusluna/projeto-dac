package io.github.dac.rhecruta.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Candidato {

    @Id
    @Column(length = 11)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false, unique = true)
    private String login;

    @Column(length = 60, nullable = false)
    private String senha;

    @OneToMany
    //Lista com o id das vagas em interesse
    private List<Integer> interesses;

    public Candidato() {

    }

    public Candidato(String nome, String login, String cpf, List<Integer> interesses) {
        this.nome = nome;
        this.login = login;
        this.cpf = cpf;
        this.interesses = interesses;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Integer> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<Integer> interesses) {
        this.interesses = interesses;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidato candidato = (Candidato) o;

        if (!nome.equals(candidato.nome)) return false;
        if (!login.equals(candidato.login)) return false;
        if (!cpf.equals(candidato.cpf)) return false;
        return interesses != null ? interesses.equals(candidato.interesses) : candidato.interesses == null;
    }

    @Override
    public int hashCode() {

        int result = nome.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + cpf.hashCode();
        result = 31 * result + (interesses != null ? interesses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Candidato{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", interesses=").append(interesses);
        sb.append('}');
        return sb.toString();
    }
}
