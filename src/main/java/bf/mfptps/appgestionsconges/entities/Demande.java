package bf.mfptps.appgestionsconges.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import bf.mfptps.appgestionsconges.enums.ETrancheDemande;

@javax.persistence.Entity
@javax.persistence.Table(name = "demande")
@SQLDelete(sql = "UPDATE demande SET deleted = true WHERE id=?")
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
public class Demande extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numero_demande", length = 254, unique = true, nullable = false)
    private String numeroDemande;

    @Column(name = "lieu_jouissance_bf", length = 254, nullable = true)
    private String lieuJouissanceBF;

    @Column(name = "lieu_jouissance_etrang", length = 254, nullable = true)
    private String lieuJouissanceEtrang;

    @Column(name = "ref_last_decision", length = 254, nullable = true)
    private String refLastDecision;

    @Column(name = "situation_snd", length = 254, nullable = true)
    private String situationSND;

    @Column(name = "duree_absence", length = 254)
    private Long dureeAbsence;

    @Column(name = "periode_debut", length = 254)
    private Date periodeDebut;

    @Column(name = "periode_fin", length = 254)
    private Date periodeFin;

    // @Column(name = "tranche", length = 254)
    // private String tranche;

    // @Column(name = "statut", length = 254)
    // private String statut;

    @ManyToOne
    @JoinColumn(name = "motif_absence_id")
    private MotifAbsence motifAbsence;

    @ManyToOne
    @JoinColumn(name = "type_demande_id")
    @JsonIgnoreProperties(value = {"demandes"}, allowSetters = true)
    private TypeDemande typeDemande ;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"demande"}, allowSetters = true)
    private Set<Document> documents = new HashSet<>();
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatusDemande statusDemande;
    
    @Column(name = "position_demande")
    @Enumerated(EnumType.STRING)
    private EPositionDemande positionDemande;
    
    @Column
    @Enumerated(EnumType.STRING)
    private ETrancheDemande trancheDemande;
    
    @Column(name="motif_rejet")
    private String motifRejet;
    @ManyToOne
    @JoinColumn(name = "acte_id")
    @JsonIgnoreProperties(value = {"demandes"}, allowSetters = true)

    private Acte acte;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getLieuJouissanceBF() {
        return lieuJouissanceBF;
    }

    public void setLieuJouissanceBF(String lieuJouissanceBF) {
        this.lieuJouissanceBF = lieuJouissanceBF;
    }

    public String getLieuJouissanceEtrang() {
        return lieuJouissanceEtrang;
    }

    public void setLieuJouissanceEtrang(String lieuJouissanceEtrang) {
        this.lieuJouissanceEtrang = lieuJouissanceEtrang;
    }

    public String getRefLastDecision() {
        return refLastDecision;
    }

    public void setRefLastDecision(String refLastDecision) {
        this.refLastDecision = refLastDecision;
    }

    public String getSituationSND() {
        return situationSND;
    }

    public void setSituationSND(String situationSND) {
        this.situationSND = situationSND;
    }

    public Long getDureeAbsence() {
        return dureeAbsence;
    }

    public void setDureeAbsence(Long dureeAbsence) {
        this.dureeAbsence = dureeAbsence;
    }

    public Date getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(Date periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public Date getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(Date periodeFin) {
        this.periodeFin = periodeFin;
    }

    public MotifAbsence getMotifAbsence() {
        return motifAbsence;
    }

    public void setMotifAbsence(MotifAbsence motifAbsence) {
        this.motifAbsence = motifAbsence;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }
    public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Set<Document> getDocuments() {
        return documents;
    }

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public EStatusDemande getStatusDemande() {
		return statusDemande;
	}

	public void setStatusDemande(EStatusDemande statusDemande) {
		this.statusDemande = statusDemande;
	}
	
    // public String getTranche() {
	// 	return tranche;
	// }

	// public void setTranche(String tranche) {
	// 	this.tranche = tranche;
	// }

	// public String getStatut() {
	// 	return statut;
	// }

	// public void setStatut(String statut) {
	// 	this.statut = statut;
	// }

	public EPositionDemande getPositionDemande() {
		return positionDemande;
	}

	public void setPositionDemande(EPositionDemande positionDemande) {
		this.positionDemande = positionDemande;
	}

	public ETrancheDemande getTrancheDemande() {
		return trancheDemande;
	}

	public void setTrancheDemande(ETrancheDemande trancheDemande) {
		this.trancheDemande = trancheDemande;
	}
	
	public String getMotifRejet() {
		return motifRejet;
	}

	public void setMotifRejet(String motifRejet) {
		this.motifRejet = motifRejet;
	}

	public Acte getActe() {
		return acte;
	}

	public void setActe(Acte acte) {
		this.acte = acte;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demande demande = (Demande) o;
        return id.equals(demande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

 
	@Override
	public String toString() {
		return "Demande { id=" + id + ", numeroDemande=" + numeroDemande + ", lieuJouissanceBF=" + lieuJouissanceBF
				+ ", lieuJouissanceEtrang=" + lieuJouissanceEtrang + ", refLastDecision=" + refLastDecision
				+ ", situationSND=" + situationSND + ", dureeAbsence=" + dureeAbsence + ", periodeDebut=" + periodeDebut
				+ ", periodeFin=" + periodeFin + ", motifAbsence="
				+ motifAbsence + ", typeDemande=" + typeDemande + ", agent=" + agent + ", documents="
				+ documents + ", statusDemande=" + statusDemande + ", positionDemande=" + positionDemande
				+ ", trancheDemande=" + trancheDemande + ", motifRejet=" + motifRejet + ", acte=" + acte  + "}";
	}
    
    
    
}
