package bf.mfptps.appnomination.service.mapper;

import bf.mfptps.appnomination.entities.Ministere;
import bf.mfptps.appnomination.service.dto.MinistereDTO;
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
