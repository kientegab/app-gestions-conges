package bf.mfptps.appgestionsconges.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

/**
 * Entite Structure
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Entity
@Table(name = "structure")
@SQLDelete(sql = "UPDATE structure SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Structure extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @Column(nullable = false)
    private String sigle;

    @Column(nullable = false)
    private String responsable;

    /**
     * STRUCTURE CAN BE 'ACTIVATED/ACTIVEE' OR 'DEACTIVATED/DESACTIVEE'
     */
    @Column(nullable = false)
    @Type(type = "yes_no")
    private boolean active = true;//if Structure always exists or no (20112021); because Structure having type MISSION can disappear

    private String telephone;

    @Column(nullable = false)
    private String emailResp;

    @Column(nullable = false)
    private String emailStruct;

    private String adresse;

    @ManyToOne
    private TypeStructure type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Structure parent;

    private String description;
    
    @ManyToMany(mappedBy = "structure")
    private Set<Agent> agent;
    

    public Structure() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.sigle = sigle;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public TypeStructure getType() {
        return type;
    }

    public void setType(TypeStructure type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmailResp() {
        return emailResp;
    }

    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }

    public String getEmailStruct() {
        return emailStruct;
    }

    public void setEmailStruct(String emailStruct) {
        this.emailStruct = emailStruct;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Structure getParent() {
        return parent;
    }

    public void setParent(Structure parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Structure{"
                + "id=" + id
                + ", libelle='" + libelle + '\''
                + ", sigle='" + sigle + '\''
                + ", responsable='" + responsable + '\''
                + ", description='" + description + '\''
                + ", type='" + type + '\''
                + ", active='" + active + '\''
                + ", telephone=" + telephone
                + ", emailResp='" + emailResp + '\''
                + ", emailStruct='" + emailStruct + '\''
                + ", parent=" + parent
                + '}';
    }
}
