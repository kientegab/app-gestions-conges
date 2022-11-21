package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StructureMapper {

    StructureDTO toDto(Structure structure);

    Structure toEntity(StructureDTO structureDTO);

    default Structure fromId(Long id) {
        if (id == null) {
            return null;
        }
        Structure structure = new Structure();
        structure.setId(id);
        return structure;
    }

}
