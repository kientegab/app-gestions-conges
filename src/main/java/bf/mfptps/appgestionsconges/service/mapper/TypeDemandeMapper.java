package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface TypeDemandeMapper extends EntityMapper<TypeDemandeDTO, TypeDemande> {

    TypeDemandeDTO toDto(TypeDemande typeDemande);

    TypeDemande toEntity(TypeDemandeDTO typeDemandeDTO);

//    default TypeDemande fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        TypeDemande typeDemande = new TypeDemande();
//        typeDemande.setId(id);
//        return typeDemande;
//    }
}
