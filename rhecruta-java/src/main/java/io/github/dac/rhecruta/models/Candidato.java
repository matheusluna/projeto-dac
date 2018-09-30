package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidato {

    @Id
    @Column(length = 11)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String senha;

    @OneToMany
    //Lista com o id das vagas em interesse
    private List<Integer> interesses;

    public Candidato() {

    }

    public Candidato(String nome, String email, String cpf, List<Integer> interesses) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.interesses = interesses;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

        if (!cpf.equals(candidato.cpf)) return false;
        if (!nome.equals(candidato.nome)) return false;
        if (!email.equals(candidato.email)) return false;
        if (!senha.equals(candidato.senha)) return false;
        return interesses != null ? interesses.equals(candidato.interesses) : candidato.interesses == null;
    }

    @Override
    public int hashCode() {

        int result = cpf.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + (interesses != null ? interesses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Candidato{");
        sb.append("cpf='").append(cpf).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", interesses=").append(interesses);
        sb.append('}');
        return sb.toString();
    }
}
