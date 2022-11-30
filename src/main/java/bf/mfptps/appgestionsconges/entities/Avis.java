package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "avis")
@SQLDelete(sql = "UPDATE avis SET deleted = true WHERE id=?")
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
public class Avis extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "avis_drh", length = 254)
    private String avisDRH;

    @Size(min = 5, max = 254)
    @Column(name = "avis_sg", length = 254)
    private String avisSG;

    @Column(name = "avis_sh", length = 254)
    private String avisSH;

    @Column(name = "avis_dg", length = 254)
    private String avisDG;
    
    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;

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
        Avis avis = (Avis) o;
        return id.equals(avis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", avisDRH='" + avisDRH + '\'' +
                ", avisSG='" + avisSG + '\'' +
                ", avisSH='" + avisSH + '\'' +
                ", avisDG='" + avisDG + '\'' +
                ", demande=" + demande +
                '}';
    }
}
