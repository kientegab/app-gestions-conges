/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.ArticleDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author HEBIE
 */
public interface ArticleService {

    ArticleDTO create(ArticleDTO articleDTO);

    ArticleDTO update(ArticleDTO articleDTO);

    Optional<ArticleDTO> findOne(long id);

    Page<ArticleDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
