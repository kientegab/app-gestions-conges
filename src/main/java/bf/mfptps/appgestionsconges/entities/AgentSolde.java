package bf.mfptps.appgestionsconges.entities;

import java.util.*;
import javax.persistence.*;
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
@javax.persistence.Entity
@javax.persistence.Table(name = "AgentSolde")
@SQLDelete(sql = "UPDATE UtilisateurSolde SET deleted = true WHERE id=?")
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

public class AgentSolde extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_matricule", length = 254)
    private String matricule;

    @Column(name = "typeDemande", length = 254)
    private String  typeDemande;

    @Column(name = "annee", length = 254)
    private int annee;

    @Column(name = "sole_restant", length = 254)
    private Long soldeRestant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(String typeDemande) {
		this.typeDemande = typeDemande;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Long getSoldeRestant() {
		return soldeRestant;
	}

	public void setSoldeRestant(Long soldeRestant) {
		this.soldeRestant = soldeRestant;
	}

    
    
}
