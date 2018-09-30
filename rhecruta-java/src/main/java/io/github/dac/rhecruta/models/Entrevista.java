package io.github.dac.rhecruta.models;

import io.github.dac.rhecruta.enums.ClassificacaoEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Entrevista {

    private Float notaDoCandidato;

    @Temporal(TemporalType.TIME)
    private LocalDateTime diaDaEntrevista;

    @Temporal(TemporalType.DATE)
    private LocalDateTime horarioDaEntrevista;

    @Enumerated
    private Enum<ClassificacaoEnum> classificacaoDoCandito;

    @OneToOne
    private Candidatura candidatura;

    public Entrevista() {

    }

    public Entrevista(Float notaDoCandidato, LocalDateTime diaDaEntrevista,
                      LocalDateTime horarioDaEntrevista, Enum<ClassificacaoEnum> classificacaoDoCandito,
                      Candidatura candidatura) {
        this.notaDoCandidato = notaDoCandidato;
        this.diaDaEntrevista = diaDaEntrevista;
        this.horarioDaEntrevista = horarioDaEntrevista;
        this.classificacaoDoCandito = classificacaoDoCandito;
        this.candidatura = candidatura;
    }

    public Float getNotaDoCandidato() {
        return notaDoCandidato;
    }

    public void setNotaDoCandidato(Float notaDoCandidato) {
        this.notaDoCandidato = notaDoCandidato;
    }

    public LocalDateTime getDiaDaEntrevista() {
        return diaDaEntrevista;
    }

    public void setDiaDaEntrevista(LocalDateTime diaDaEntrevista) {
        this.diaDaEntrevista = diaDaEntrevista;
    }

    public LocalDateTime getHorarioDaEntrevista() {
        return horarioDaEntrevista;
    }

    public void setHorarioDaEntrevista(LocalDateTime horarioDaEntrevista) {
        this.horarioDaEntrevista = horarioDaEntrevista;
    }

    public Enum<ClassificacaoEnum> getClassificacaoDoCandito() {
        return classificacaoDoCandito;
    }

    public void setClassificacaoDoCandito(Enum<ClassificacaoEnum> classificacaoDoCandito) {
        this.classificacaoDoCandito = classificacaoDoCandito;
    }

    public Candidatura getCandidatura() {
        return candidatura;
    }

    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entrevista that = (Entrevista) o;

        if (!diaDaEntrevista.equals(that.diaDaEntrevista)) return false;
        if (!horarioDaEntrevista.equals(that.horarioDaEntrevista)) return false;
        if (classificacaoDoCandito != null ? !classificacaoDoCandito.equals(that.classificacaoDoCandito) : that.classificacaoDoCandito != null)
            return false;
        return candidatura.equals(that.candidatura);
    }

    @Override
    public int hashCode() {

        int result = notaDoCandidato != null ? notaDoCandidato.hashCode() : 0;
        result = 31 * result + diaDaEntrevista.hashCode();
        result = 31 * result + horarioDaEntrevista.hashCode();
        result = 31 * result + (classificacaoDoCandito != null ? classificacaoDoCandito.hashCode() : 0);
        result = 31 * result + candidatura.hashCode();
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Entrevista{");
        sb.append("notaDoCandidato=").append(notaDoCandidato);
        sb.append(", diaDaEntrevista=").append(diaDaEntrevista);
        sb.append(", horarioDaEntrevista=").append(horarioDaEntrevista);
        sb.append(", classificacaoDoCandito=").append(classificacaoDoCandito);
        sb.append(", candidatura=").append(candidatura);
        sb.append('}');
        return sb.toString();
    }
}
