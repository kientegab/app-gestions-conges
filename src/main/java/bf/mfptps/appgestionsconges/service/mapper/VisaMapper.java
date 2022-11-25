package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Visa;
import bf.mfptps.appgestionsconges.service.dto.VisaDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface VisaMapper extends EntityMapper<VisaDTO, Visa> {

    VisaDTO toDto(Visa visa);

    Visa toEntity(VisaDTO visaDTO);

    default Visa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Visa visa = new Visa();
        visa.setId(id);
        return visa;
    }
}
