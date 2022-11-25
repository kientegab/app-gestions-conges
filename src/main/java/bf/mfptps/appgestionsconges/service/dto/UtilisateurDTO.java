package bf.mfptps.appgestionsconges.service.dto;

import java.util.Objects;

public class UtilisateurDTO {

    private Long id;
    private String nom;

    private String prenom;

    private String motDPasse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMotDPasse() {
        return motDPasse;
    }

    public void setMotDPasse(String motDPasse) {
        this.motDPasse = motDPasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurDTO that = (UtilisateurDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", motDPasse='" + motDPasse + '\'' +
                '}';
    }
}
