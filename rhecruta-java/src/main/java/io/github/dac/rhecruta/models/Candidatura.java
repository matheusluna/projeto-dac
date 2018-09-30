package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.dac.rhecruta.enums.ParecerEnum;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidatura {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer vagaId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File curriculoCandidato;

    @Enumerated
    private Enum<ParecerEnum> parecer;

    @Temporal(TemporalType.DATE)
    private LocalDate dataCandidatura;

    @OneToOne
    private Candidato candidato;

    public Candidatura() {

    }

    public Candidatura(Integer vagaId, File curriculoCandidato, Enum<ParecerEnum> parecer,
                       LocalDate dataCandidatura, Candidato candidato) {
        this.vagaId = vagaId;
        this.curriculoCandidato = curriculoCandidato;
        this.parecer = parecer;
        this.dataCandidatura = dataCandidatura;
        this.candidato = candidato;
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

    public File getCurriculoCandidato() {
        return curriculoCandidato;
    }

    public void setCurriculoCandidato(File curriculoCandidato) {
        this.curriculoCandidato = curriculoCandidato;
    }

    public Enum<ParecerEnum> getParecer() {
        return parecer;
    }

    public void setParecer(Enum<ParecerEnum> parecer) {
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Candidatura that = (Candidatura) o;

        if (!id.equals(that.id)) return false;
        if (!vagaId.equals(that.vagaId)) return false;
        if (!curriculoCandidato.equals(that.curriculoCandidato)) return false;
        if (parecer != null ? !parecer.equals(that.parecer) : that.parecer != null) return false;
        if (!dataCandidatura.equals(that.dataCandidatura)) return false;
        return candidato.equals(that.candidato);
    }

    @Override
    public int hashCode() {

        int result = id.hashCode();
        result = 31 * result + vagaId.hashCode();
        result = 31 * result + curriculoCandidato.hashCode();
        result = 31 * result + (parecer != null ? parecer.hashCode() : 0);
        result = 31 * result + dataCandidatura.hashCode();
        result = 31 * result + candidato.hashCode();
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Candidatura{");
        sb.append("id=").append(id);
        sb.append(", vagaId=").append(vagaId);
        sb.append(", curriculoCandidato=").append(curriculoCandidato);
        sb.append(", parecer=").append(parecer);
        sb.append(", dataCandidatura=").append(dataCandidatura);
        sb.append(", candidato=").append(candidato);
        sb.append('}');
        return sb.toString();
    }
}
