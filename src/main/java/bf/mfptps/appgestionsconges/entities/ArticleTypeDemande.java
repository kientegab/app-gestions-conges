package bf.mfptps.appgestionsconges.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author TEGUERA
 */
@Entity
@Table(name = "article_type_demande")
@SQLDelete(sql = "UPDATE article_type_demande SET deleted = true WHERE id=?")
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
public class ArticleTypeDemande extends  CommonEntity{

//    @EmbeddedId
//    ArticleTypeDemandeKey id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @MapsId("articleId")
    @JoinColumn(name = "article_id")
    Article article;


    @ManyToOne
    @MapsId("typeDemandeId")
    @JoinColumn(name = "type_demande_id")
    TypeDemande typeDemande;

    @NotNull
    private Long numeroOrdre;

    public ArticleTypeDemande() {
    }

//    public ArticleTypeDemande(ArticleTypeDemandeKey id, Article article, TypeDemande typeDemande, Long numeroOrdre) {
//        this.id = id;
//        this.article = article;
//        this.typeDemande = typeDemande;
//        this.numeroOrdre = numeroOrdre;
//    }


    public ArticleTypeDemande(Long id, Article article, TypeDemande typeDemande, Long numeroOrdre) {
        this.id = id;
        this.article = article;
        this.typeDemande = typeDemande;
        this.numeroOrdre = numeroOrdre;
    }

//    public ArticleTypeDemandeKey getId() {
//        return id;
//    }
//
//    public void setId(ArticleTypeDemandeKey id) {
//        this.id = id;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Long getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Long numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ArticleTypeDemande that = (ArticleTypeDemande) o;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "ArticleTypeDemande{" +
//                "id=" + id +
//                ", article=" + article +
//                ", typeDemande=" + typeDemande +
//                ", numeroOrdre=" + numeroOrdre +
//                '}';
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleTypeDemande that = (ArticleTypeDemande) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ArticleTypeDemande{" +
                "id=" + id +
                ", article=" + article +
                ", typeDemande=" + typeDemande +
                ", numeroOrdre=" + numeroOrdre +
                '}';
    }
}
