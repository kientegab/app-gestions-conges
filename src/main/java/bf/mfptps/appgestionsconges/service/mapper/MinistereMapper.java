package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.service.dto.MinistereDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MinistereMapper {

    @Mappings({
        @Mapping(target = "code", source = "ministere.code")
    })
    MinistereDTO toDto(Ministere ministere);

    @Mappings({
        @Mapping(target = "code", source = "ministereDTO.code")
    })
    Ministere toEntity(MinistereDTO ministereDTO);
}
