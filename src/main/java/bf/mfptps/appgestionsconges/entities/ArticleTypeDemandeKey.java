package bf.mfptps.appgestionsconges.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleTypeDemandeKey implements Serializable {
    Long articleId;
    Long typeDemandeId;

    public ArticleTypeDemandeKey() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTypeDemandeId() {
        return typeDemandeId;
    }

    public void setTypeDemandeId(Long typeDemandeId) {
        this.typeDemandeId = typeDemandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleTypeDemandeKey that = (ArticleTypeDemandeKey) o;
        return articleId.equals(that.articleId) && typeDemandeId.equals(that.typeDemandeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, typeDemandeId);
    }
}
