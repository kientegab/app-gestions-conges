package bf.mfptps.appgestionsconges.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mappings({
        @Mapping(target = "reference", source = "document.reference")
    })
    DocumentDTO toDto(Document document);

    @Mappings({
        @Mapping(target = "reference", source = "documentDTO.reference")
    })
    Document toEntity(DocumentDTO documentDTO);
}
