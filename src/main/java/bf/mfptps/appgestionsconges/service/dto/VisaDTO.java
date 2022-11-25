package bf.mfptps.appgestionsconges.service.dto;


import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VisaDTO  {

    private Long id;

    private String code;

    private String libelle;

    private Set<TypeVisaDTO> typeVisas = new HashSet<TypeVisaDTO>();

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

    public Set<TypeVisaDTO> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisaDTO> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public void addTypeVisa(TypeVisaDTO typeVisa) {
        this.typeVisas.add(typeVisa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisaDTO visa = (VisaDTO) o;
        return id.equals(visa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Visa{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", typeVisas=" + typeVisas +
                '}';
    }
}
