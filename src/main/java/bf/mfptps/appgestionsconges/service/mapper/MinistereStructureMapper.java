package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.service.dto.MinistereStructureDTO;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MinistereStructureMapper {

    @Mappings({
        @Mapping(target = "statut", source = "statut")
    })
    MinistereStructureDTO toDto(MinistereStructure ministereS);

    @Mappings({
        @Mapping(target = "statut", source = "statut")
    })
    MinistereStructure toEntity(MinistereStructureDTO ministereSDTO);

    @Mappings({
        @Mapping(target = "id", source = "structure.id"),
        @Mapping(target = "libelle", source = "structure.libelle"),
        @Mapping(target = "sigle", source = "structure.sigle"),
        @Mapping(target = "responsable", source = "structure.responsable"),
        @Mapping(target = "active", source = "structure.active"),
        @Mapping(target = "description", source = "structure.description"),
        @Mapping(target = "telephone", source = "structure.telephone"),
        @Mapping(target = "type", source = "structure.type"),
        @Mapping(target = "emailResp", source = "structure.emailResp"),
        @Mapping(target = "emailStruct", source = "structure.emailStruct"),
        @Mapping(target = "adresse", source = "structure.adresse"),
        @Mapping(target = "ministere", source = "ministere"),
        @Mapping(target = "parent", source = "structure.parent")})
    StructureDTO toStructureDTO(MinistereStructure ministereStructure);
}
