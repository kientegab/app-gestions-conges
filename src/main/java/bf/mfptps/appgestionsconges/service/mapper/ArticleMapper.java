/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Article;
import bf.mfptps.appgestionsconges.service.dto.ArticleDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author HEBIE
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    ArticleDTO toDto(Article article);

    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}
