package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Avaliador {

    @Id
    @Column(length = 11)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String senha;

    public Avaliador() {

    }

    public Avaliador(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Avaliador avaliador = (Avaliador) o;

        if (!nome.equals(avaliador.nome)) return false;
        if (!email.equals(avaliador.email)) return false;
        if (!senha.equals(avaliador.senha)) return false;
        return cpf.equals(avaliador.cpf);
    }

    @Override
    public int hashCode() {

        int result = nome.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + senha.hashCode();
        result = 31 * result + cpf.hashCode();
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Avaliador{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
