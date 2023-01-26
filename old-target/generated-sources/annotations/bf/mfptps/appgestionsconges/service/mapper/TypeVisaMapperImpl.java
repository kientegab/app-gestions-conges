package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class TypeVisaMapperImpl implements TypeVisaMapper {

    @Override
    public List<TypeVisa> toEntity(List<TypeVisaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TypeVisa> list = new ArrayList<TypeVisa>( dtoList.size() );
        for ( TypeVisaDTO typeVisaDTO : dtoList ) {
            list.add( toEntity( typeVisaDTO ) );
        }

        return list;
    }

    @Override
    public List<TypeVisaDTO> toDto(List<TypeVisa> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TypeVisaDTO> list = new ArrayList<TypeVisaDTO>( entityList.size() );
        for ( TypeVisa typeVisa : entityList ) {
            list.add( toDto( typeVisa ) );
        }

        return list;
    }

    @Override
    public TypeVisaDTO toDto(TypeVisa typeVisa) {
        if ( typeVisa == null ) {
            return null;
        }

        TypeVisaDTO typeVisaDTO = new TypeVisaDTO();

        typeVisaDTO.setCreatedBy( typeVisa.getCreatedBy() );
        typeVisaDTO.setCreatedDate( typeVisa.getCreatedDate() );
        typeVisaDTO.setLastModifiedBy( typeVisa.getLastModifiedBy() );
        typeVisaDTO.setLastModifiedDate( typeVisa.getLastModifiedDate() );
        typeVisaDTO.setDeleted( typeVisa.isDeleted() );
        typeVisaDTO.setId( typeVisa.getId() );
        typeVisaDTO.setVisa( typeVisa.getVisa() );
        typeVisaDTO.setTypeDemande( typeVisa.getTypeDemande() );
        typeVisaDTO.setNumeroOrdre( typeVisa.getNumeroOrdre() );

        return typeVisaDTO;
    }

    @Override
    public TypeVisa toEntity(TypeVisaDTO typeVisaDTO) {
        if ( typeVisaDTO == null ) {
            return null;
        }

        TypeVisa typeVisa = new TypeVisa();

        typeVisa.setCreatedBy( typeVisaDTO.getCreatedBy() );
        typeVisa.setCreatedDate( typeVisaDTO.getCreatedDate() );
        typeVisa.setLastModifiedBy( typeVisaDTO.getLastModifiedBy() );
        typeVisa.setLastModifiedDate( typeVisaDTO.getLastModifiedDate() );
        typeVisa.setDeleted( typeVisaDTO.isDeleted() );
        typeVisa.setId( typeVisaDTO.getId() );
        typeVisa.setVisa( typeVisaDTO.getVisa() );
        typeVisa.setTypeDemande( typeVisaDTO.getTypeDemande() );
        typeVisa.setNumeroOrdre( typeVisaDTO.getNumeroOrdre() );

        return typeVisa;
    }
}
