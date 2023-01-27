package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import bf.mfptps.appgestionsconges.service.dto.ArticleTypeDemandeDTO;
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
public class ArticleTypeDemandeMapperImpl implements ArticleTypeDemandeMapper {

    @Override
    public List<ArticleTypeDemande> toEntity(List<ArticleTypeDemandeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ArticleTypeDemande> list = new ArrayList<ArticleTypeDemande>( dtoList.size() );
        for ( ArticleTypeDemandeDTO articleTypeDemandeDTO : dtoList ) {
            list.add( toEntity( articleTypeDemandeDTO ) );
        }

        return list;
    }

    @Override
    public List<ArticleTypeDemandeDTO> toDto(List<ArticleTypeDemande> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ArticleTypeDemandeDTO> list = new ArrayList<ArticleTypeDemandeDTO>( entityList.size() );
        for ( ArticleTypeDemande articleTypeDemande : entityList ) {
            list.add( toDto( articleTypeDemande ) );
        }

        return list;
    }

    @Override
    public ArticleTypeDemandeDTO toDto(ArticleTypeDemande articleTypeDemande) {
        if ( articleTypeDemande == null ) {
            return null;
        }

        ArticleTypeDemandeDTO articleTypeDemandeDTO = new ArticleTypeDemandeDTO();

        articleTypeDemandeDTO.setId( articleTypeDemande.getId() );
        articleTypeDemandeDTO.setArticle( articleTypeDemande.getArticle() );
        articleTypeDemandeDTO.setTypeDemande( articleTypeDemande.getTypeDemande() );
        articleTypeDemandeDTO.setNumeroOrdre( articleTypeDemande.getNumeroOrdre() );

        return articleTypeDemandeDTO;
    }

    @Override
    public ArticleTypeDemande toEntity(ArticleTypeDemandeDTO articleTypeDemandeDTO) {
        if ( articleTypeDemandeDTO == null ) {
            return null;
        }

        ArticleTypeDemande articleTypeDemande = new ArticleTypeDemande();

        articleTypeDemande.setId( articleTypeDemandeDTO.getId() );
        articleTypeDemande.setArticle( articleTypeDemandeDTO.getArticle() );
        articleTypeDemande.setTypeDemande( articleTypeDemandeDTO.getTypeDemande() );
        articleTypeDemande.setNumeroOrdre( articleTypeDemandeDTO.getNumeroOrdre() );

        return articleTypeDemande;
    }
}
