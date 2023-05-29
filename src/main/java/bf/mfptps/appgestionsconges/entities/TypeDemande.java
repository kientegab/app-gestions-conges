package bf.mfptps.appgestionsconges.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@javax.persistence.Entity
@javax.persistence.Table(name = "type_demande")
@SQLDelete(sql = "UPDATE type_demande SET deleted = true WHERE id=?")
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
public class TypeDemande extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
//        @Size(min = 1, max = 50)
    @Column(name = "libelle", unique = true, nullable = false)
    private String libelle;

    @Column(name = "mode_paie")
    private Boolean modePaie;

    @Column(name = "description")
    private String description;

    @Column(name = "remote_value")
    private Long remoteValue;
    /*
    @OneToMany(mappedBy = "typeDemande", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"typeDemande"}, allowSetters = true)
    private Set<TypeVisa> typeVisas = new HashSet<>();

    @OneToMany(mappedBy = "typeDemande", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ArticleTypeDemande> articleTypeDemandes;
     */

    @OneToMany(mappedBy = "typeDemande", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TypeVisa> typeVisas = new HashSet<>();

    @OneToMany(mappedBy = "typeDemande", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ArticleTypeDemande> articleTypeDemandes;

    @ManyToMany(mappedBy = "typeDemande", cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Ampliation> ampliation;

    @Column(name = "solde_annuel")
    private Long soldeAnnuel;

    @Column(name = "code")
    private String code;

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

    public Boolean getModePaie() {
        return modePaie;
    }

    public void setModePaie(Boolean modePaie) {
        this.modePaie = modePaie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    public Set<TypeVisa> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisa> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public void addTypeVisa(TypeVisa typeVisa) {
        this.typeVisas.add(typeVisa);
    }
    

    public Set<ArticleTypeDemande> getArticleTypeDemandes() {
        return articleTypeDemandes;
    }

    public void setArticleTypeDemandes(Set<ArticleTypeDemande> articleTypeDemandes) {
        this.articleTypeDemandes = articleTypeDemandes;
    }
     */
    public Long getSoldeAnnuel() {
        return soldeAnnuel;
    }

    public void setSoldeAnnuel(Long soldeAnnuel) {
        this.soldeAnnuel = soldeAnnuel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRemoteValue() {
        return remoteValue;
    }

    public void setRemoteValue(Long remoteValue) {
        this.remoteValue = remoteValue;
    }

    public List<Ampliation> getAmpliation() {
        return ampliation;
    }

    public void setAmpliation(List<Ampliation> ampliation) {
        this.ampliation = ampliation;
    }

    public Set<TypeVisa> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisa> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public Set<ArticleTypeDemande> getArticleTypeDemandes() {
        return articleTypeDemandes;
    }

    public void setArticleTypeDemandes(Set<ArticleTypeDemande> articleTypeDemandes) {
        this.articleTypeDemandes = articleTypeDemandes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeDemande that = (TypeDemande) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /*
=======


>>>>>>> 5cc0eebf86aed74e83f4899c9faebfb52d619e41
    @Override
    public String toString() {
        return "TypeDemande{" + "id=" + id + ", libelle=" + libelle + ", modePaie=" + modePaie + ", description=" + description + ", remoteValue=" + remoteValue + ", typeVisas=" + typeVisas + ", articleTypeDemandes=" + articleTypeDemandes + ", soldeAnnuel=" + soldeAnnuel + ", code=" + code + '}';
    }
     */
}
