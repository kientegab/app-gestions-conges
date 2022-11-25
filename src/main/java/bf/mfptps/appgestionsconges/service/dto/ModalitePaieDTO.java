/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import java.util.Objects;

/**
 *
 * @author HEBIE
 */
public class ModalitePaieDTO extends CommonEntity {

    private Long id;

    private String code;

    private String libelle;

    private Long remoteID;

    private Long defaultPRMID;

    private TypeDemandeDTO typeDemande;

    private boolean desactiver;

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

    public TypeDemandeDTO getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemandeDTO typeDemande) {
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
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final ModalitePaieDTO other = (ModalitePaieDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ModalitePaieDTO{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + ", remoteID=" + remoteID + ", defaultPRMID=" + defaultPRMID + ", typeDemande=" + typeDemande + ", desactiver=" + desactiver + '}';
    }

}
