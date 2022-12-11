package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import bf.mfptps.appgestionsconges.enums.EStatusActe;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EStatusActe status;
    
    @Column(name = "ampliation")
    private String ampliation;
    
    @ManyToOne
    @JoinColumn(name = "typeacte_id")
    private TypeActe typeActe;
    

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
    
    
}
