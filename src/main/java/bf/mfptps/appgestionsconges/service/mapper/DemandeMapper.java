package bf.mfptps.appgestionsconges.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;

@Mapper(componentModel = "spring")
public interface DemandeMapper {

    @Mappings({
        @Mapping(target = "numeroDemande", source = "demande.numeroDemande")
    })
    DemandeDTO toDto(Demande demande);

    @Mappings({
        @Mapping(target = "numeroDemande", source = "demandeDTO.numeroDemande")
    })
    Demande toEntity(DemandeDTO demandeDTO);

}
