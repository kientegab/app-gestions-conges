package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author TEGUERA
 */

public interface ArticleTypeDemandeRepository extends JpaRepository<ArticleTypeDemande, Long>, JpaSpecificationExecutor<ArticleTypeDemande> {
}
