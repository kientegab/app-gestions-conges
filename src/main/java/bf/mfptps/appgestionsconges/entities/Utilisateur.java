package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "utilisateur")
@SQLDelete(sql = "UPDATE utilisateur SET deleted = true WHERE id=?")
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

public class Utilisateur extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom", length = 254)
    private String nom;

    @Column(name = "prenom", length = 254)
    private String prenom;

    @Column(name = "mot_de_passe", length = 254)
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
        Utilisateur that = (Utilisateur) o;
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
