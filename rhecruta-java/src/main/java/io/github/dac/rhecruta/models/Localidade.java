package io.github.dac.rhecruta.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Localidade implements Serializable {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;

    {
        this.numero = -1;
        this.rua = "NÃO DEFINIDO";
        this.bairro = "NÃO DEFINIDO";
        this.cidade = "NÃO DEFINIDO";
        this.estado = "NÃO DEFINIDO";
    }

    public Localidade() {

    }

    public Localidade(String rua, String bairro, String cidade, String estado, Integer numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Localidade that = (Localidade) o;

        if (rua != null ? !rua.equals(that.rua) : that.rua != null) return false;
        if (bairro != null ? !bairro.equals(that.bairro) : that.bairro != null) return false;
        if (cidade != null ? !cidade.equals(that.cidade) : that.cidade != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;
        return numero != null ? numero.equals(that.numero) : that.numero == null;
    }

    @Override
    public int hashCode() {
        int result = rua != null ? rua.hashCode() : 0;
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Localidade{");
        sb.append("rua='").append(rua).append('\'');
        sb.append(", bairro='").append(bairro).append('\'');
        sb.append(", cidade='").append(cidade).append('\'');
        sb.append(", estado='").append(estado).append('\'');
        sb.append(", numero=").append(numero);
        sb.append('}');
        return sb.toString();
    }

}
