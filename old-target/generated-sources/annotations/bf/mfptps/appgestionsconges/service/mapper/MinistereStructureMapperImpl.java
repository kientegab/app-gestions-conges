package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.entities.TypeStructure;
import bf.mfptps.appgestionsconges.service.dto.MinistereStructureDTO;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class MinistereStructureMapperImpl implements MinistereStructureMapper {

    @Override
    public MinistereStructureDTO toDto(MinistereStructure ministereS) {
        if ( ministereS == null ) {
            return null;
        }

        MinistereStructureDTO ministereStructureDTO = new MinistereStructureDTO();

        ministereStructureDTO.setStatut( String.valueOf( ministereS.isStatut() ) );

        return ministereStructureDTO;
    }

    @Override
    public MinistereStructure toEntity(MinistereStructureDTO ministereSDTO) {
        if ( ministereSDTO == null ) {
            return null;
        }

        MinistereStructure ministereStructure = new MinistereStructure();

        if ( ministereSDTO.getStatut() != null ) {
            ministereStructure.setStatut( Boolean.parseBoolean( ministereSDTO.getStatut() ) );
        }

        return ministereStructure;
    }

    @Override
    public StructureDTO toStructureDTO(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }

        StructureDTO structureDTO = new StructureDTO();

        structureDTO.setId( ministereStructureStructureId( ministereStructure ) );
        structureDTO.setLibelle( ministereStructureStructureLibelle( ministereStructure ) );
        structureDTO.setSigle( ministereStructureStructureSigle( ministereStructure ) );
        structureDTO.setResponsable( ministereStructureStructureResponsable( ministereStructure ) );
        structureDTO.setActive( ministereStructureStructureActive( ministereStructure ) );
        structureDTO.setDescription( ministereStructureStructureDescription( ministereStructure ) );
        structureDTO.setTelephone( ministereStructureStructureTelephone( ministereStructure ) );
        structureDTO.setType( ministereStructureStructureType( ministereStructure ) );
        structureDTO.setEmailResp( ministereStructureStructureEmailResp( ministereStructure ) );
        structureDTO.setEmailStruct( ministereStructureStructureEmailStruct( ministereStructure ) );
        structureDTO.setAdresse( ministereStructureStructureAdresse( ministereStructure ) );
        structureDTO.setMinistere( ministereStructure.getMinistere() );
        structureDTO.setParent( ministereStructureStructureParent( ministereStructure ) );

        return structureDTO;
    }

    private Long ministereStructureStructureId(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        Long id = structure.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String ministereStructureStructureLibelle(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String libelle = structure.getLibelle();
        if ( libelle == null ) {
            return null;
        }
        return libelle;
    }

    private String ministereStructureStructureSigle(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String sigle = structure.getSigle();
        if ( sigle == null ) {
            return null;
        }
        return sigle;
    }

    private String ministereStructureStructureResponsable(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String responsable = structure.getResponsable();
        if ( responsable == null ) {
            return null;
        }
        return responsable;
    }

    private boolean ministereStructureStructureActive(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return false;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return false;
        }
        boolean active = structure.isActive();
        return active;
    }

    private String ministereStructureStructureDescription(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String description = structure.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private String ministereStructureStructureTelephone(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String telephone = structure.getTelephone();
        if ( telephone == null ) {
            return null;
        }
        return telephone;
    }

    private TypeStructure ministereStructureStructureType(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        TypeStructure type = structure.getType();
        if ( type == null ) {
            return null;
        }
        return type;
    }

    private String ministereStructureStructureEmailResp(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String emailResp = structure.getEmailResp();
        if ( emailResp == null ) {
            return null;
        }
        return emailResp;
    }

    private String ministereStructureStructureEmailStruct(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String emailStruct = structure.getEmailStruct();
        if ( emailStruct == null ) {
            return null;
        }
        return emailStruct;
    }

    private String ministereStructureStructureAdresse(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        String adresse = structure.getAdresse();
        if ( adresse == null ) {
            return null;
        }
        return adresse;
    }

    private Structure ministereStructureStructureParent(MinistereStructure ministereStructure) {
        if ( ministereStructure == null ) {
            return null;
        }
        Structure structure = ministereStructure.getStructure();
        if ( structure == null ) {
            return null;
        }
        Structure parent = structure.getParent();
        if ( parent == null ) {
            return null;
        }
        return parent;
    }
}
