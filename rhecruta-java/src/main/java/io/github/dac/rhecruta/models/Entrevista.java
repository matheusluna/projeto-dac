package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dac.rhecruta.enums.ClassificacaoEnum;
import io.github.dac.rhecruta.models.converters.DateConverter;
import io.github.dac.rhecruta.models.converters.TimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float notaDoCandidato;

    @Convert(converter = DateConverter.class)
    private LocalDateTime diaDaEntrevista;

    @Convert(converter = TimeConverter.class)
    private LocalDateTime horarioDaEntrevista;

    @Enumerated(EnumType.STRING)
    private ClassificacaoEnum classificacaoDoCandito;

    @OneToOne(fetch = FetchType.LAZY)
    private Candidatura candidatura;

    public Entrevista() {

    }

    public Entrevista(Integer id, Float notaDoCandidato, LocalDateTime diaDaEntrevista,
                      LocalDateTime horarioDaEntrevista, ClassificacaoEnum classificacaoDoCandito,
                      Candidatura candidatura) {
        this.id = id;
        this.notaDoCandidato = notaDoCandidato;
        this.diaDaEntrevista = diaDaEntrevista;
        this.horarioDaEntrevista = horarioDaEntrevista;
        this.classificacaoDoCandito = classificacaoDoCandito;
        this.candidatura = candidatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ClassificacaoEnum getClassificacaoDoCandito() {
        return classificacaoDoCandito;
    }

    public void setClassificacaoDoCandito(ClassificacaoEnum classificacaoDoCandito) {
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

        if (!id.equals(that.id)) return false;
        if (notaDoCandidato != null ? !notaDoCandidato.equals(that.notaDoCandidato) : that.notaDoCandidato != null)
            return false;
        if (diaDaEntrevista != null ? !diaDaEntrevista.equals(that.diaDaEntrevista) : that.diaDaEntrevista != null)
            return false;
        if (horarioDaEntrevista != null ? !horarioDaEntrevista.equals(that.horarioDaEntrevista) : that.horarioDaEntrevista != null)
            return false;
        if (classificacaoDoCandito != null ? !classificacaoDoCandito.equals(that.classificacaoDoCandito) : that.classificacaoDoCandito != null)
            return false;
        return candidatura.equals(that.candidatura);
    }

    @Override
    public int hashCode() {

        int result = id.hashCode();
        result = 31 * result + (notaDoCandidato != null ? notaDoCandidato.hashCode() : 0);
        result = 31 * result + (diaDaEntrevista != null ? diaDaEntrevista.hashCode() : 0);
        result = 31 * result + (horarioDaEntrevista != null ? horarioDaEntrevista.hashCode() : 0);
        result = 31 * result + (classificacaoDoCandito != null ? classificacaoDoCandito.hashCode() : 0);
        result = 31 * result + candidatura.hashCode();
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Entrevista{");
        sb.append("id=").append(id);
        sb.append(", notaDoCandidato=").append(notaDoCandidato);
        sb.append(", diaDaEntrevista=").append(diaDaEntrevista);
        sb.append(", horarioDaEntrevista=").append(horarioDaEntrevista);
        sb.append(", classificacaoDoCandito=").append(classificacaoDoCandito);
        sb.append(", candidatura=").append(candidatura);
        sb.append('}');
        return sb.toString();
    }
}
