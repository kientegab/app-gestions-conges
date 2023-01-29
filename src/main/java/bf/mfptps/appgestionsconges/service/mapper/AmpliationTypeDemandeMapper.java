package bf.mfptps.appgestionsconges.service.mapper;


import bf.mfptps.appgestionsconges.entities.AmpliationTypeDemande;
import bf.mfptps.appgestionsconges.service.dto.AmpliationTypeDemandeDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author ZINA
 */
@Mapper(componentModel = "spring")
public interface AmpliationTypeDemandeMapper {

    AmpliationTypeDemandeDTO toDto(AmpliationTypeDemande ampliationTypeDemande);
    AmpliationTypeDemande toEntity(AmpliationTypeDemandeDTO ampliationTypeDemandeDTO);
}
