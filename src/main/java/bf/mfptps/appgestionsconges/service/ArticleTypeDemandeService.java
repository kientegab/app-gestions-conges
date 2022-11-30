package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.ArticleTypeDemandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleTypeDemandeService {

    ArticleTypeDemandeDTO create(ArticleTypeDemandeDTO articleTypeDemandeDTO);

    ArticleTypeDemandeDTO update(ArticleTypeDemandeDTO articleTypeDemandeDTO);

    Optional<ArticleTypeDemandeDTO> findOne(Long id);

    Page<ArticleTypeDemandeDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
