package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Avis;
import bf.mfptps.appgestionsconges.service.dto.AvisDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface AvisMapper extends EntityMapper<AvisDTO, Avis> {

    AvisDTO toDto(Avis avis);

    Avis toEntity(AvisDTO avisDTO);

    default Avis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Avis avis = new Avis();
        avis.setId(id);
        return avis;
    }
}
