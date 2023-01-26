package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:05+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class StructureMapperImpl implements StructureMapper {

    @Override
    public StructureDTO toDto(Structure structure) {
        if ( structure == null ) {
            return null;
        }

        StructureDTO structureDTO = new StructureDTO();

        structureDTO.setId( structure.getId() );
        structureDTO.setSigle( structure.getSigle() );
        structureDTO.setResponsable( structure.getResponsable() );
        structureDTO.setLibelle( structure.getLibelle() );
        structureDTO.setDescription( structure.getDescription() );
        structureDTO.setActive( structure.isActive() );
        structureDTO.setTelephone( structure.getTelephone() );
        structureDTO.setEmailResp( structure.getEmailResp() );
        structureDTO.setEmailStruct( structure.getEmailStruct() );
        structureDTO.setAdresse( structure.getAdresse() );
        structureDTO.setType( structure.getType() );
        structureDTO.setParent( structure.getParent() );

        return structureDTO;
    }

    @Override
    public Structure toEntity(StructureDTO structureDTO) {
        if ( structureDTO == null ) {
            return null;
        }

        Structure structure = new Structure();

        structure.setId( structureDTO.getId() );
        structure.setLibelle( structureDTO.getLibelle() );
        structure.setDescription( structureDTO.getDescription() );
        structure.setSigle( structureDTO.getSigle() );
        structure.setResponsable( structureDTO.getResponsable() );
        structure.setType( structureDTO.getType() );
        structure.setActive( structureDTO.isActive() );
        structure.setTelephone( structureDTO.getTelephone() );
        structure.setEmailResp( structureDTO.getEmailResp() );
        structure.setEmailStruct( structureDTO.getEmailStruct() );
        structure.setAdresse( structureDTO.getAdresse() );
        structure.setParent( structureDTO.getParent() );

        return structure;
    }
}
