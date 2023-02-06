package bf.mfptps.appgestionsconges.service.dto;


import bf.mfptps.appgestionsconges.entities.Article;
import bf.mfptps.appgestionsconges.entities.ArticleTypeDemandeKey;
import bf.mfptps.appgestionsconges.entities.TypeDemande;

import java.util.Objects;

/**
 *
 * @author TEGUERA
 */

public class ArticleTypeDemandeDTO {

//    ArticleTypeDemandeKey id;
    private Long id;

    private Article article;

    private  TypeDemande typeDemande;

    private Long numeroOrdre;

    public ArticleTypeDemandeDTO() {
    }

//    public ArticleTypeDemandeDTO(ArticleTypeDemandeKey id, Article article, TypeDemande typeDemande, Long numeroOrdre) {
//        this.id = id;
//        this.article = article;
//        this.typeDemande = typeDemande;
//        this.numeroOrdre = numeroOrdre;
//    }


    public ArticleTypeDemandeDTO(Long id, Article article, TypeDemande typeDemande, Long numeroOrdre) {
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
//        ArticleTypeDemandeDTO that = (ArticleTypeDemandeDTO) o;
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
//        return "ArticleTypeDemandeDTO{" +
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
        ArticleTypeDemandeDTO that = (ArticleTypeDemandeDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ArticleTypeDemandeDTO{" +
                "id=" + id +
                ", article=" + article +
                ", typeDemande=" + typeDemande +
                ", numeroOrdre=" + numeroOrdre +
                '}';
    }
}
