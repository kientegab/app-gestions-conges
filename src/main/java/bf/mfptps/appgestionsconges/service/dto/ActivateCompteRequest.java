/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import java.util.Date;

/**
 *
 * @author HEBIE
 */
public class ActivateCompteRequest {

    //  private String nom;
    //   private String prenom;
    private String matricule;

    private Date dateNaissance;

    private Date dateRecrutement;

    public ActivateCompteRequest(String matricule, Date dateNaissance, Date dateRecrutement) {
        this.matricule = matricule;
        this.dateNaissance = dateNaissance;
        this.dateRecrutement = dateRecrutement;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

}
