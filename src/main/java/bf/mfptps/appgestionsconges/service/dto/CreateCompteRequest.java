/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.ToString;

/**
 *
 * @author HEBIE
 */
@ToString(exclude = "password")
public class CreateCompteRequest {

    @NotBlank
    private String matricule;

    private Date dateNaissance;//date de naissance

    private String lieuNaissance;//lieu de naissance

    private Date dateRecrutement;//correspond a la date d'integration

    private StructureDTO structure;

    private String matriculeResp;

    @NotBlank
    @Size(min = 4, message = "Le mot de passe doit etre d'au moins 4 caracteres")
    private String password;

    @Email
    @NotBlank
    private String email;

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

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StructureDTO getStructure() {
        return structure;
    }

    public void setStructure(StructureDTO structure) {
        this.structure = structure;
    }

    public String getMatriculeResp() {
        return matriculeResp;
    }

    public void setMatriculeResp(String matriculeResp) {
        this.matriculeResp = matriculeResp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.matricule);
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
        final CreateCompteRequest other = (CreateCompteRequest) obj;
        return Objects.equals(this.matricule, other.matricule);
    }

    public CreateCompteRequest() {
    }

    public CreateCompteRequest(String matricule, Date dateNaissance, String lieuNaissance, Date dateRecrutement, StructureDTO structure, String matriculeResp, String password, String email) {
        this.matricule = matricule;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dateRecrutement = dateRecrutement;
        this.structure = structure;
        this.matriculeResp = matriculeResp;
        this.password = password;
        this.email = email;
    }

}
