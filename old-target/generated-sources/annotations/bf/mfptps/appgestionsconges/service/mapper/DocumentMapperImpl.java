package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:05+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentDTO toDto(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setReference( document.getReference() );
        documentDTO.setId( document.getId() );
        documentDTO.setUrl( document.getUrl() );
        documentDTO.setDemande( document.getDemande() );

        return documentDTO;
    }

    @Override
    public Document toEntity(DocumentDTO documentDTO) {
        if ( documentDTO == null ) {
            return null;
        }

        Document document = new Document();

        document.setReference( documentDTO.getReference() );
        document.setId( documentDTO.getId() );
        document.setUrl( documentDTO.getUrl() );
        document.setDemande( documentDTO.getDemande() );

        return document;
    }
}
