/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

/**
 *
 * @author HEBIE
 */
@Entity
@Table(name = "payprovider")
@SQLDelete(sql = "UPDATE payprovider SET deleted = true WHERE id=?")
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
public class ModalitePaie extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "code")
    private String code;

    @Column(name = "remoteid")
    private Long remoteID;

    @Column(name = "defaultprmid")
    private Long defaultPRMID;

    @ManyToOne
    @JoinColumn(name = "type_demande")
    private TypeDemande typeDemande;

    @Type(type = "yes_no")
    @Column(name = "desactiver")
    private boolean desactiver;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(Long remoteID) {
        this.remoteID = remoteID;
    }

    public Long getDefaultPRMID() {
        return defaultPRMID;
    }

    public void setDefaultPRMID(Long defaultPRMID) {
        this.defaultPRMID = defaultPRMID;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public boolean isDesactiver() {
        return desactiver;
    }

    public void setDesactiver(boolean desactiver) {
        this.desactiver = desactiver;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final ModalitePaie other = (ModalitePaie) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ModalitePaie{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + ", remoteID=" + remoteID + ", defaultPRMID=" + defaultPRMID + ", typeDemande=" + typeDemande + ", desactiver=" + desactiver + '}';
    }

}
