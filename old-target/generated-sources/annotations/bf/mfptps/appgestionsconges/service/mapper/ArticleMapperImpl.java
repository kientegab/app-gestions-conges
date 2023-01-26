package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Article;
import bf.mfptps.appgestionsconges.service.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public List<Article> toEntity(List<ArticleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Article> list = new ArrayList<Article>( dtoList.size() );
        for ( ArticleDTO articleDTO : dtoList ) {
            list.add( toEntity( articleDTO ) );
        }

        return list;
    }

    @Override
    public List<ArticleDTO> toDto(List<Article> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ArticleDTO> list = new ArrayList<ArticleDTO>( entityList.size() );
        for ( Article article : entityList ) {
            list.add( toDto( article ) );
        }

        return list;
    }

    @Override
    public ArticleDTO toDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDTO articleDTO = new ArticleDTO();

        articleDTO.setCreatedBy( article.getCreatedBy() );
        articleDTO.setCreatedDate( article.getCreatedDate() );
        articleDTO.setLastModifiedBy( article.getLastModifiedBy() );
        articleDTO.setLastModifiedDate( article.getLastModifiedDate() );
        articleDTO.setDeleted( article.isDeleted() );
        articleDTO.setId( article.getId() );
        articleDTO.setCode( article.getCode() );
        articleDTO.setAttributLibelle( article.getAttributLibelle() );

        return articleDTO;
    }

    @Override
    public Article toEntity(ArticleDTO articleDTO) {
        if ( articleDTO == null ) {
            return null;
        }

        Article article = new Article();

        article.setCreatedBy( articleDTO.getCreatedBy() );
        article.setCreatedDate( articleDTO.getCreatedDate() );
        article.setLastModifiedBy( articleDTO.getLastModifiedBy() );
        article.setLastModifiedDate( articleDTO.getLastModifiedDate() );
        article.setDeleted( articleDTO.isDeleted() );
        article.setId( articleDTO.getId() );
        article.setCode( articleDTO.getCode() );
        article.setAttributLibelle( articleDTO.getAttributLibelle() );

        return article;
    }
}
