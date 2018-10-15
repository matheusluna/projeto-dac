package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dac.rhecruta.enums.ParecerEnum;
import io.github.dac.rhecruta.models.converters.DateConverter;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer vagaId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String curriculoCandidato;

    @Enumerated(EnumType.STRING)
    private ParecerEnum parecer;

    @Convert(converter = DateConverter.class)
    private LocalDate dataCandidatura;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidato candidato;

    {
        this.curriculoCandidato = null;
        this.dataCandidatura = LocalDate.now();
        this.parecer = ParecerEnum.NAO_DEFINIDO;
    }

    public Candidatura() {

    }

    public Candidatura(Integer vagaId, String curriculoCandidato, ParecerEnum parecer,
                       LocalDate dataCandidatura, Candidato candidato) {
        this.vagaId = vagaId;
        this.curriculoCandidato = curriculoCandidato;
        this.parecer = parecer;
        this.dataCandidatura = dataCandidatura;
        this.candidato = candidato;
    }

    private Candidatura(Candidatura candidatura) {
        this.vagaId = candidatura.getVagaId();
        this.curriculoCandidato = candidatura.getCurriculoCandidato();
        this.parecer = candidatura.getParecer();
        this.dataCandidatura = candidatura.getDataCandidatura();
        this.candidato = candidatura.getCandidato();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVagaId() {
        return vagaId;
    }

    public void setVagaId(Integer vagaId) {
        this.vagaId = vagaId;
    }

    public String getCurriculoCandidato() {
        return curriculoCandidato;
    }

    public void setCurriculoCandidato(String curriculoCandidato) {
        this.curriculoCandidato = curriculoCandidato;
    }

    public ParecerEnum getParecer() {
        return parecer;
    }

    public void setParecer(ParecerEnum parecer) {
        this.parecer = parecer;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Candidatura clone() {
        return new Candidatura(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidatura that = (Candidatura) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (vagaId != null ? !vagaId.equals(that.vagaId) : that.vagaId != null) return false;
        if (curriculoCandidato != null ? !curriculoCandidato.equals(that.curriculoCandidato) : that.curriculoCandidato != null)
            return false;
        if (parecer != that.parecer) return false;
        if (dataCandidatura != null ? !dataCandidatura.equals(that.dataCandidatura) : that.dataCandidatura != null)
            return false;
        return candidato != null ? candidato.equals(that.candidato) : that.candidato == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vagaId != null ? vagaId.hashCode() : 0);
        result = 31 * result + (curriculoCandidato != null ? curriculoCandidato.hashCode() : 0);
        result = 31 * result + (parecer != null ? parecer.hashCode() : 0);
        result = 31 * result + (dataCandidatura != null ? dataCandidatura.hashCode() : 0);
        result = 31 * result + (candidato != null ? candidato.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Candidatura{");
        sb.append("id=").append(id);
        sb.append(", vagaId=").append(vagaId);
        sb.append(", curriculoCandidato='").append(curriculoCandidato).append('\'');
        sb.append(", parecer=").append(parecer);
        sb.append(", dataCandidatura=").append(dataCandidatura);
        sb.append(", candidato=").append(candidato);
        sb.append('}');
        return sb.toString();
    }

}
