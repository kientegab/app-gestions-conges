package bf.mfptps.appgestionsconges.entities;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.*;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Entity
@Table(name = "ministere")
@SQLDelete(sql = "UPDATE ministere SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Ministere extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String sigle;

    private String description;
    
    @ManyToMany
    @JoinTable( name = "ministere_structure",
    	    joinColumns = @JoinColumn( name = "structure_id" ),
    	    inverseJoinColumns = @JoinColumn( name = "ministere_id" ) )
    @JsonIgnore
    private List<Structure> structure;

    public Ministere() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public List<Structure> getStructure() {
		return structure;
	}

	public void setStructure(List<Structure> structure) {
		this.structure = structure;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle.toUpperCase();
    }

    @Override
    public String toString() {
        return "Ministere [code=" + code + ", libelle=" + libelle + ", sigle=" + sigle + "]";
    }

}
