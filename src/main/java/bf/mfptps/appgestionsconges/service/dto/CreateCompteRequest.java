/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

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

    public CreateCompteRequest(String matricule, String password, String email) {
        this.matricule = matricule;
        this.password = password;
        this.email = email;
    }

}
