package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import bf.mfptps.appgestionsconges.enums.EStatusDemande;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "demande")
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

    @Column(name = "lieu_jouissance_bf", length = 254)
    private String lieuJouissanceBF;

    @Column(name = "lieu_jouissance_etrang", length = 254)
    private String lieuJouissanceEtrang;

    @Column(name = "ref_last_decision", length = 254)
    private String refLastDecision;

    @Column(name = "situation_snd", length = 254)
    private String situationSND;

    @Column(name = "duree_absence", length = 254)
    private Long dureeAbsence;

    @Column(name = "periode_debut", length = 254)
    private Date periodeDebut;

    @Column(name = "periode_fin", length = 254)
    private Date periodeFin;

    @ManyToOne
    @JoinColumn(name = "motif_absence_id")
    private MotifAbsence motifAbsence;

    @ManyToOne
    @JoinColumn(name = "type_demande_id")
    private TypeDemande typeDemande ;
    
    

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur ;
    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "demande" }, allowSetters = true)
    private Set<Document> documents = new HashSet<>();
    
    @Column(name = "status")
    private EStatusDemande statusDemande;

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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demande demande = (Demande) o;
        return id.equals(demande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", numeroDemande='" + numeroDemande + '\'' +
                ", lieuJouissanceBF='" + lieuJouissanceBF + '\'' +
                ", lieuJouissanceEtrang='" + lieuJouissanceEtrang + '\'' +
                ", refLastDecision='" + refLastDecision + '\'' +
                ", situationSND='" + situationSND + '\'' +
                ", dureeAbsence=" + dureeAbsence +
                ", periodeDebut=" + periodeDebut +
                ", periodeFin=" + periodeFin +
                ", motifAbsence=" + motifAbsence +
                ", typeDemande=" + typeDemande +
                ", utilisateur=" + utilisateur +
                ", documents=" + documents +
                ", statusDemande=" + statusDemande +
                '}';
    }
}
