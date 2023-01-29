package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import bf.mfptps.appgestionsconges.entities.TypeVisa;

import java.util.Objects;
import java.util.Set;

public class VisaDTO {

    private Long id;

    private String code;

    private String libelle;
/*
    private Set<TypeVisa> typeVisas;
*/
    public VisaDTO() {
    }
/*
    public VisaDTO(Long id, String code, String libelle, Set<TypeVisa> typeVisas) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.typeVisas = typeVisas;
    }*/

    public VisaDTO(Long id, String code, String libelle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
/*
    public Set<TypeVisa> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisa> typeVisas) {
        this.typeVisas = typeVisas;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisaDTO visaDTO = (VisaDTO) o;
        return id.equals(visaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
/*
    @Override
    public String toString() {
        return "VisaDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", typeVisas=" + typeVisas +
                '}';
    }
    */
}
