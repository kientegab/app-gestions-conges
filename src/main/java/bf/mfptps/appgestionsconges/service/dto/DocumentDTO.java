package bf.mfptps.appgestionsconges.service.dto;

import java.util.Objects;

import bf.mfptps.appgestionsconges.entities.Demande;

public class DocumentDTO {

    private Long id;

    private String reference;

    private String url;

    private Demande demande;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String reference, String url, Demande demande) {
        this.id = id;
        this.reference = reference;
        this.url = url;
        this.demande = demande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDTO that = (DocumentDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", url='" + url + '\'' +
                ", demande=" + demande +
                '}';
    }
}
