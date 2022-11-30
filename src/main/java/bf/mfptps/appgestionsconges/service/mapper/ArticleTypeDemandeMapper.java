package bf.mfptps.appgestionsconges.service.mapper;


import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import bf.mfptps.appgestionsconges.entities.ArticleTypeDemandeKey;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.service.dto.ArticleTypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface ArticleTypeDemandeMapper extends EntityMapper<ArticleTypeDemandeDTO, ArticleTypeDemande> {

    ArticleTypeDemandeDTO toDto(ArticleTypeDemande articleTypeDemande);

    ArticleTypeDemande toEntity(ArticleTypeDemandeDTO articleTypeDemandeDTO);

//    default ArticleTypeDemande fromId(ArticleTypeDemandeKey id) {
//        if (id == null) {
//            return null;
//        }
//        ArticleTypeDemande articleTypeDemande = new ArticleTypeDemande();
//        articleTypeDemande.setId(id);
//        return articleTypeDemande;
//    }
}
