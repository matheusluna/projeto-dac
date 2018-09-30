package io.github.dac.rhecruta.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vaga {

    private int id;
    private String title;
    private String description;
    private String workplace;
    private String company_name;
    private String company_email;
    private String application_link;
    private String created_at;
    private String requirements;


    public Vaga() {

    }

    public Vaga(int id, String title, String description, String workplace, String company_name,
                String company_email, String application_link, String created_at, String requirements) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.workplace = workplace;
        this.company_name = company_name;
        this.company_email = company_email;
        this.application_link = application_link;
        this.created_at = created_at;
        this.requirements = requirements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getApplication_link() {
        return application_link;
    }

    public void setApplication_link(String application_link) {
        this.application_link = application_link;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vaga vaga = (Vaga) o;

        if (id != vaga.id) return false;
        if (title != null ? !title.equals(vaga.title) : vaga.title != null) return false;
        if (description != null ? !description.equals(vaga.description) : vaga.description != null) return false;
        if (workplace != null ? !workplace.equals(vaga.workplace) : vaga.workplace != null) return false;
        if (company_name != null ? !company_name.equals(vaga.company_name) : vaga.company_name != null) return false;
        if (company_email != null ? !company_email.equals(vaga.company_email) : vaga.company_email != null)
            return false;
        if (application_link != null ? !application_link.equals(vaga.application_link) : vaga.application_link != null)
            return false;
        if (created_at != null ? !created_at.equals(vaga.created_at) : vaga.created_at != null) return false;
        return requirements != null ? requirements.equals(vaga.requirements) : vaga.requirements == null;
    }

    @Override
    public int hashCode() {

        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (workplace != null ? workplace.hashCode() : 0);
        result = 31 * result + (company_name != null ? company_name.hashCode() : 0);
        result = 31 * result + (company_email != null ? company_email.hashCode() : 0);
        result = 31 * result + (application_link != null ? application_link.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Vaga{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", workplace='").append(workplace).append('\'');
        sb.append(", company_name='").append(company_name).append('\'');
        sb.append(", company_email='").append(company_email).append('\'');
        sb.append(", application_link='").append(application_link).append('\'');
        sb.append(", created_at='").append(created_at).append('\'');
        sb.append(", requirements='").append(requirements).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
