
package bf.mfptps.appgestionsconges.service.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author HEBIE
 */
    public class TypeDemandeDTO  {

        private Long id;
        private String libelle;
        private Boolean modePaie;
        private String description;
        private Set<TypeVisaDTO> typeVisas = new HashSet<>();
        private Long soldeAnnuel;

        private String code;


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

        public Boolean getModePaie() {
            return modePaie;
        }

        public void setModePaie(Boolean modePaie) {
            this.modePaie = modePaie;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    public Set<TypeVisaDTO> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisaDTO> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public void addTypeVisa(TypeVisaDTO typeVisa) {
        this.typeVisas.add(typeVisa);
    }
    
    

    public Long getSoldeAnnuel() {
		return soldeAnnuel;
	}

	public void setSoldeAnnuel(Long soldeAnnuel) {
		this.soldeAnnuel = soldeAnnuel;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDemandeDTO that = (TypeDemandeDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeDemandeDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", modePaie=" + modePaie +
                ", description='" + description + '\'' +
                ", typeVisas=" + typeVisas +
                ", soldeAnnuel=" + soldeAnnuel +
                ", code='" + code + '\'' +
                '}';
    }
}
