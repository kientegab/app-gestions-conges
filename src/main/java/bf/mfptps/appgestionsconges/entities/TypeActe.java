package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import bf.mfptps.appgestionsconges.enums.EPorteActe;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    
    @ManyToOne
    @Enumerated(EnumType.STRING)
    private EPorteActe typeActe;
    

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

	public EPorteActe getTypeActe() {
		return typeActe;
	}

	public void setTypeActe(EPorteActe typeActe) {
		this.typeActe = typeActe;
	}

    s
}
