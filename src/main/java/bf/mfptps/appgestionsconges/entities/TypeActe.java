package bf.mfptps.appgestionsconges.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import bf.mfptps.appgestionsconges.enums.EPorteActe;

@Entity
@Table(name = "type_acte")
@SQLDelete(sql = "UPDATE type_acte SET deleted = true WHERE id=?")
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

public class TypeActe extends CommonEntity{


	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @Column(name = "code", nullable = false, unique = true)
    private String reference;
    
    @Column(name = "libelle")
    private String libelle;
    
    @Column(name = "template_uri")
    private String templateUri;
    
    @Column(name = "porte_acte")
    @Enumerated(EnumType.STRING)
    private EPorteActe porteActe;
    

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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTemplateUri() {
		return templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}

	public EPorteActe getPorteActe() {
		return porteActe;
	}

	public void setPorteActe(EPorteActe porteActe) {
		this.porteActe = porteActe;
	}
}
