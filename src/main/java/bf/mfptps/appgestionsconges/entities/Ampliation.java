/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.entities;

import bf.mfptps.appgestionsconges.config.Constants;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author HEBIE
 */
@Entity
@Table(name = "ampliation")
@SQLDelete(sql = "UPDATE ampliation SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ampliation extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 10)
    //@Column(length = 10)
    private String code;

    @ManyToMany
    @JoinTable(name = "ampliation_type_demande",
            joinColumns = @JoinColumn(name = "ampliation_id"),
            inverseJoinColumns = @JoinColumn(name = "type_demande_id"))
    private List<TypeDemande> typeDemande;

    @Column(nullable = false)
    private String libelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TypeDemande> getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(List<TypeDemande> typeDemande) {
        this.typeDemande = typeDemande;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
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
        final Ampliation other = (Ampliation) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Ampliation{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + '}';
    }

}
