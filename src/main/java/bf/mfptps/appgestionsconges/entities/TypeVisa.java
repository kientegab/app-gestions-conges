package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author TEGUERA
 */
@Entity
@Table(name = "type_visa")
@SQLDelete(sql = "UPDATE type_visa SET deleted = true WHERE id=?")
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
public class TypeVisa extends CommonEntity {
    @EmbeddedId
    private TypeVisaKey id = new TypeVisaKey();

    @ManyToOne
    @MapsId("visaId")
    @JoinColumn(name = "visa_id")
    private Visa visa;


    @ManyToOne
    @MapsId("typeDemandeId")
    @JoinColumn(name = "type_demande_id")
    private TypeDemande typeDemande;

    @NotNull
    private Long numeroOrdre;

    public TypeVisa() {
    }

    public TypeVisa(TypeVisaKey id, Visa visa, TypeDemande typeDemande, Long numeroOrdre) {
        this.id = id;
        this.visa = visa;
        this.typeDemande = typeDemande;
        this.numeroOrdre = numeroOrdre;
    }

    public TypeVisaKey getId() {
        return id;
    }

    public void setId(TypeVisaKey id) {
        this.id = id;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Long getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Long numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVisa typeVisa = (TypeVisa) o;
        return id.equals(typeVisa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeVisa{" +
                "id=" + id +
                ", visa=" + visa +
                ", typeDemande=" + typeDemande +
                ", numeroOrdre=" + numeroOrdre +
                '}';
    }
}
