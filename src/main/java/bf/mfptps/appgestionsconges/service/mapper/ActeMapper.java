package bf.mfptps.appgestionsconges.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;

@Mapper(componentModel = "spring")
public interface ActeMapper {

    @Mappings({
        @Mapping(target = "reference", source = "acte.reference")
    })
    ActeDTO toDto(Acte acte);

    @Mappings({
        @Mapping(target = "reference", source = "acteDTO.reference")
    })
    Acte toEntity(ActeDTO acteDTO);
}
