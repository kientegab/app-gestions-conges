package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ampliation_type_demande")
@SQLDelete(sql = "UPDATE ampliation_type_demande SET deleted = true WHERE id=?")
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
public class AmpliationTypeDemande extends  CommonEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    Ampliation ampliation;

    @ManyToOne
    TypeDemande typeDemande;

    public AmpliationTypeDemande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ampliation getAmpliation() {
        return ampliation;
    }

    public void setAmpliation(Ampliation ampliation) {
        this.ampliation = ampliation;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }
}
