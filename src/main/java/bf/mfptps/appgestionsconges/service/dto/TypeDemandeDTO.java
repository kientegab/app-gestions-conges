/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author HEBIE
 */
public class TypeDemandeDTO {

    private Long id;

    private String libelle;

    private Boolean modePaie;

    private String description;

    private Set<TypeVisa> typeVisas = new HashSet<TypeVisa>();

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

    public Set<TypeVisa> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisa> typeVisas) {
        this.typeVisas = typeVisas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TypeDemandeDTO other = (TypeDemandeDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TypeDemandeDTO{" + "id=" + id + ", libelle=" + libelle + ", modePaie=" + modePaie + ", description=" + description + ", typeVisas=" + typeVisas + '}';
    }

}
