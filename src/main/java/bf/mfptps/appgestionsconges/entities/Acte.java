package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import bf.mfptps.appgestionsconges.enums.EStatusActe;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "acte")
@SQLDelete(sql = "UPDATE acte SET deleted = true WHERE id=?")
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

public class Acte extends CommonEntity{


	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @Column(name = "reference", nullable = false, unique = true)
    private String reference;
    
    @Column
    private String enteteMinistere;
    
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatusActe status;
    
    @Column(name = "ampliation")
    private String ampliation;
    
    @ManyToOne
    @JoinColumn(name = "typeacte_id")
    private TypeActe typeActe;
    
    @Column(name = "annee")
    private String annee;
    
    @OneToMany(mappedBy = "acte", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"acte"}, allowSetters = true)
    private Set<Demande> demandes = new HashSet<>();

    @Column(name = "creator_names")
    private String nomPrenomCreator;
    @Column(name="creator_title")
    private String titreCreator;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public EStatusActe getStatus() {
		return status;
	}

	public void setStatus(EStatusActe status) {
		this.status = status;
	}

	public String getAmpliation() {
		return ampliation;
	}

	public void setAmpliation(String ampliation) {
		this.ampliation = ampliation;
	}

	public String getEnteteMinistere() {
		return enteteMinistere;
	}

	public void setEnteteMinistere(String enteteMinistere) {
		this.enteteMinistere = enteteMinistere;
	}

	public TypeActe getTypeActe() {
		return typeActe;
	}

	public void setTypeActe(TypeActe typeActe) {
		this.typeActe = typeActe;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public Set<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(Set<Demande> demandes) {
		this.demandes = demandes;
	}

	public String getNomPrenomCreator() {
		return nomPrenomCreator;
	}

	public void setNomPrenomCreator(String nomPrenomCreator) {
		this.nomPrenomCreator = nomPrenomCreator;
	}

	public String getTitreCreator() {
		return titreCreator;
	}

	public void setTitreCreator(String titreCreator) {
		this.titreCreator = titreCreator;
	}
    
    
}
