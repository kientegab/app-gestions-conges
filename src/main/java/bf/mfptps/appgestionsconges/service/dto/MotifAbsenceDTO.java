package bf.mfptps.appgestionsconges.service.dto;

import java.util.Objects;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */
public class MotifAbsenceDTO {

    private Long id;

    private String libelle;

    public MotifAbsenceDTO() {
    }

    public MotifAbsenceDTO(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotifAbsenceDTO that = (MotifAbsenceDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MotifAbsenceDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
