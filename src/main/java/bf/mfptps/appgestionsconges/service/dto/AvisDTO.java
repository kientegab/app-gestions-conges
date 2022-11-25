package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import bf.mfptps.appgestionsconges.entities.Demande;

import java.util.Objects;

/**
 *
 * @author TEGUERA
 */
public class AvisDTO extends CommonEntity {

    private Long id;

    private String avisDRH;

    private String avisSG;

    private String avisSH;

    private String avisDG;

    private Demande demande;

    public AvisDTO() {
    }

    public AvisDTO(Long id, String avisDRH, String avisSG, String avisSH, String avisDG, Demande demande) {
        this.id = id;
        this.avisDRH = avisDRH;
        this.avisSG = avisSG;
        this.avisSH = avisSH;
        this.avisDG = avisDG;
        this.demande = demande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvisDRH() {
        return avisDRH;
    }

    public void setAvisDRH(String avisDRH) {
        this.avisDRH = avisDRH;
    }

    public String getAvisSG() {
        return avisSG;
    }

    public void setAvisSG(String avisSG) {
        this.avisSG = avisSG;
    }

    public String getAvisSH() {
        return avisSH;
    }

    public void setAvisSH(String avisSH) {
        this.avisSH = avisSH;
    }

    public String getAvisDG() {
        return avisDG;
    }

    public void setAvisDG(String avisDG) {
        this.avisDG = avisDG;
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
        AvisDTO avisDTO = (AvisDTO) o;
        return id.equals(avisDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AvisDTO{" +
                "id=" + id +
                ", avisDRH='" + avisDRH + '\'' +
                ", avisSG='" + avisSG + '\'' +
                ", avisSH='" + avisSH + '\'' +
                ", avisDG='" + avisDG + '\'' +
                ", demande=" + demande +
                '}';
    }
}
