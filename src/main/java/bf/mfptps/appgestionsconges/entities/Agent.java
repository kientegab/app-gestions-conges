package bf.mfptps.appgestionsconges.entities;

import bf.mfptps.appgestionsconges.config.Constants;
import bf.mfptps.appgestionsconges.enums.Sexe;
import com.fasterxml.jackson.annotation.*;
import java.time.Instant;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "agent")
@SQLDelete(sql = "UPDATE agent SET deleted = true WHERE id=?")
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
public class Agent extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String matricule;

    @Column(length = 5, nullable = false)
    private String cleMatricule;//lettre clee du matricule

    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true, nullable = true)
    private String email;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 150)
    @Column(name = "prenom", length = 150)
    private String prenom;

    @Size(max = 50)
    @Column(name = "nom_jeune_fille", length = 50)
    private String nomJeuneFille;//nom de jeune fille

    @Column(nullable = false)
    private Sexe sexe;//sexe de l'agent

    private Date dateNaissance;//date de naissance

    private String lieuNaissance;//lieu de naissance

    @Column(unique = true, nullable = true)
    private String noCNIB;//le nip de la cnib

    private Date dateRecrutement;//correspond a la date d'integration

    private String qualite;//libelle qualite de l'agent.  ex: stagiaire, fonct. titulaire

    private Date dateQualite;

    private String categorie;//libelle de la categorie. ex: A,B,C

    private String echelle;//libelle de l'echelle.  ex: 1, 3

    private String echellon;//libelle de l'echellon. correspond a l'avancement/bonification

    private String position;//la position administration de l'agent. ex: retraite, activite

    private String grade;

    private String affectation;//dernier poste où est affecté l'agent a l'instant T

    @NotNull
    @Column(nullable = false)
    private boolean actif = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "agent_profile",
            joinColumns = {
                @JoinColumn(name = "agent_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "profile_id", referencedColumnName = "id")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Profile> profiles = new HashSet<>();

    @ManyToOne//many agents to one corps
    private Corps corps;//correspond a Emploi de l'agent

    /*@ManyToOne(optional = true)
    private Structure structure;*/
    public Agent() {
    }

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
        this.matricule = StringUtils.lowerCase(matricule);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

//    public Structure getStructure() {
//        return structure;
//    }
//
//    public void setStructure(Structure structure) {
//        this.structure = structure;
//    }
    public String getCleMatricule() {
        return cleMatricule;
    }

    public void setCleMatricule(String cleMatricule) {
        this.cleMatricule = cleMatricule;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNoCNIB() {
        return noCNIB;
    }

    public void setNoCNIB(String noCNIB) {
        this.noCNIB = noCNIB;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getQualite() {
        return qualite;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    public Date getDateQualite() {
        return dateQualite;
    }

    public void setDateQualite(Date dateQualite) {
        this.dateQualite = dateQualite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEchelle() {
        return echelle;
    }

    public void setEchelle(String echelle) {
        this.echelle = echelle;
    }

    public String getEchellon() {
        return echellon;
    }

    public void setEchellon(String echellon) {
        this.echellon = echellon;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAffectation() {
        return affectation;
    }

    public void setAffectation(String affectation) {
        this.affectation = affectation;
    }

    public Corps getCorps() {
        return corps;
    }

    public void setCorps(Corps corps) {
        this.corps = corps;
    }

    public void activate() {
        this.actif = true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Agent other = (Agent) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agent{" + "id=" + id + ", matricule=" + matricule + ", cleMatricule=" + cleMatricule + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", nomJeuneFille=" + nomJeuneFille + ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", lieuNaissance=" + lieuNaissance + ", noCNIB=" + noCNIB + ", dateRecrutement=" + dateRecrutement + ", qualite=" + qualite + ", dateQualite=" + dateQualite + ", categorie=" + categorie + ", echelle=" + echelle + ", echellon=" + echellon + ", position=" + position + ", grade=" + grade + ", affectation=" + affectation + ", actif=" + actif + ", activationKey=" + activationKey + ", resetKey=" + resetKey + ", resetDate=" + resetDate + ", telephone=" + telephone + ", profiles=" + profiles + ", corps=" + corps + '}';
    }

}
