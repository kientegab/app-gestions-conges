package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
    @Table(name = "type_demande")
    @SQLDelete(sql = "UPDATE type_demande SET deleted = true WHERE id=?")
    @Where(clause = "deleted = false")
    @FilterDef(
            name = "deletedFilter",
            parameters = @ParamDef(name = "isDeleted", type = "boolean")
    )
    @Filter(
            name = "deletedFilter",
            condition = "deleted = :isDeleted"
    )
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public class TypeDemande extends CommonEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
        @SequenceGenerator(name = "sequenceGenerator")
        private Long id;

        @NotNull
//        @Size(min = 1, max = 50)
        @Column(name = "libelle", unique = true, nullable = false)
        private String libelle;

        @Column(name = "mode_paie",length = 254, unique = true)
        private Boolean modePaie;

        @Column(name = "description")
        private String description;
        

        @OneToMany(mappedBy = "typeDemande")
        private Set<TypeVisa> typeVisas = new HashSet<TypeVisa>();
        
        @Column(name ="solde_annuel")
        private String soldeAnnuel;


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

    public void addTypeVisa(TypeVisa typeVisa) {
        this.typeVisas.add(typeVisa);
    }
    
    

    public String getSoldeAnnuel() {
		return soldeAnnuel;
	}

	public void setSoldeAnnuel(String soldeAnnuel) {
		this.soldeAnnuel = soldeAnnuel;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDemande that = (TypeDemande) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeDemande{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", modePaie=" + modePaie +
                ", description='" + description + '\'' +
                ", typeVisas=" + typeVisas +
                ", soldeAnnuel=" + soldeAnnuel +
                '}';
    }
}
