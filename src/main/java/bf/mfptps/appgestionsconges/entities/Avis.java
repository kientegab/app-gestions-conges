package bf.mfptps.appgestionsconges.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author TEGUERA
 */
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

   // @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "avis_drh", length = 254)
    private String avisDRH;
    
    @Column(name = "date_avis_drh")
    private Date dateAvisDRH;

    @Size(min = 5, max = 254)
    @Column(name = "avis_sg", length = 254)
    private String avisSG;
    
    @Column(name = "date_avis_sg")
    private Date dateAvisSG;

    @Column(name = "avis_sh", length = 254)
    private String avisSH;
    
    @Column(name = "date_avis_sh")
    private Date dateAvisSH;

    @Column(name = "avis_dg", length = 254)
    private String avisDG;

    @Column(name = "date_avis_dg")
    private Date dateAvisDG;

//    @ManyToOne(cascade = CascadeType.ALL)
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

    
    
    public Date getDateAvisDRH() {
		return dateAvisDRH;
	}

	public void setDateAvisDRH(Date dateAvisDRH) {
		this.dateAvisDRH = dateAvisDRH;
	}

	public Date getDateAvisSG() {
		return dateAvisSG;
	}

	public void setDateAvisSG(Date dateAvisSG) {
		this.dateAvisSG = dateAvisSG;
	}

	public Date getDateAvisSH() {
		return dateAvisSH;
	}

	public void setDateAvisSH(Date dateAvisSH) {
		this.dateAvisSH = dateAvisSH;
	}

	public Date getDateAvisDG() {
		return dateAvisDG;
	}

	public void setDateAvisDG(Date dateAvisDG) {
		this.dateAvisDG = dateAvisDG;
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
