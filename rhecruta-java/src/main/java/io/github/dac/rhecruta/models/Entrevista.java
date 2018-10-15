package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dac.rhecruta.enums.ClassificacaoEnum;
import io.github.dac.rhecruta.models.converters.DateConverter;
import io.github.dac.rhecruta.models.converters.TimeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float notaDoCandidato;

    @Embedded
    private Localidade local;

    @Convert(converter = DateConverter.class)
    private LocalDate diaDaEntrevista;

    @Convert(converter = TimeConverter.class)
    private LocalTime horarioDaEntrevista;

    @Enumerated(EnumType.STRING)
    private ClassificacaoEnum classificacaoDoCandito;

    @OneToOne(fetch = FetchType.LAZY)
    private Candidatura candidatura;

    {
        this.local = new Localidade();
        this.notaDoCandidato = Float.valueOf("-1.0");
        this.classificacaoDoCandito = ClassificacaoEnum.NAO_DEFINIDO;
    }

    public Entrevista() {

    }

    public Entrevista(Float notaDoCandidato, Localidade local, LocalDate diaDaEntrevista, LocalTime horarioDaEntrevista,
                      ClassificacaoEnum classificacaoDoCandito, Candidatura candidatura) {
        this.notaDoCandidato = notaDoCandidato;
        this.local = local;
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

    public Localidade getLocal() {
        return local;
    }

    public void setLocal(Localidade local) {
        this.local = local;
    }

    public LocalDate getDiaDaEntrevista() {
        return diaDaEntrevista;
    }

    public void setDiaDaEntrevista(LocalDate diaDaEntrevista) {
        this.diaDaEntrevista = diaDaEntrevista;
    }

    public LocalTime getHorarioDaEntrevista() {
        return horarioDaEntrevista;
    }

    public void setHorarioDaEntrevista(LocalTime horarioDaEntrevista) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (notaDoCandidato != null ? !notaDoCandidato.equals(that.notaDoCandidato) : that.notaDoCandidato != null)
            return false;
        if (local != null ? !local.equals(that.local) : that.local != null) return false;
        if (diaDaEntrevista != null ? !diaDaEntrevista.equals(that.diaDaEntrevista) : that.diaDaEntrevista != null)
            return false;
        if (horarioDaEntrevista != null ? !horarioDaEntrevista.equals(that.horarioDaEntrevista) : that.horarioDaEntrevista != null)
            return false;
        if (classificacaoDoCandito != that.classificacaoDoCandito) return false;
        return candidatura != null ? candidatura.equals(that.candidatura) : that.candidatura == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (notaDoCandidato != null ? notaDoCandidato.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        result = 31 * result + (diaDaEntrevista != null ? diaDaEntrevista.hashCode() : 0);
        result = 31 * result + (horarioDaEntrevista != null ? horarioDaEntrevista.hashCode() : 0);
        result = 31 * result + (classificacaoDoCandito != null ? classificacaoDoCandito.hashCode() : 0);
        result = 31 * result + (candidatura != null ? candidatura.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entrevista{");
        sb.append("id=").append(id);
        sb.append(", notaDoCandidato=").append(notaDoCandidato);
        sb.append(", local=").append(local);
        sb.append(", diaDaEntrevista=").append(diaDaEntrevista);
        sb.append(", horarioDaEntrevista=").append(horarioDaEntrevista);
        sb.append(", classificacaoDoCandito=").append(classificacaoDoCandito);
        sb.append(", candidatura=").append(candidatura);
        sb.append('}');
        return sb.toString();
    }
}
