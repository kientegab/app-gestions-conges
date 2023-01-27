package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.entities.Visa;
import bf.mfptps.appgestionsconges.service.dto.VisaDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:05+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class VisaMapperImpl implements VisaMapper {

    @Override
    public List<Visa> toEntity(List<VisaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Visa> list = new ArrayList<Visa>( dtoList.size() );
        for ( VisaDTO visaDTO : dtoList ) {
            list.add( toEntity( visaDTO ) );
        }

        return list;
    }

    @Override
    public List<VisaDTO> toDto(List<Visa> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VisaDTO> list = new ArrayList<VisaDTO>( entityList.size() );
        for ( Visa visa : entityList ) {
            list.add( toDto( visa ) );
        }

        return list;
    }

    @Override
    public VisaDTO toDto(Visa visa) {
        if ( visa == null ) {
            return null;
        }

        VisaDTO visaDTO = new VisaDTO();

        visaDTO.setCreatedBy( visa.getCreatedBy() );
        visaDTO.setCreatedDate( visa.getCreatedDate() );
        visaDTO.setLastModifiedBy( visa.getLastModifiedBy() );
        visaDTO.setLastModifiedDate( visa.getLastModifiedDate() );
        visaDTO.setDeleted( visa.isDeleted() );
        visaDTO.setId( visa.getId() );
        visaDTO.setCode( visa.getCode() );
        visaDTO.setLibelle( visa.getLibelle() );
        Set<TypeVisa> set = visa.getTypeVisas();
        if ( set != null ) {
            visaDTO.setTypeVisas( new HashSet<TypeVisa>( set ) );
        }

        return visaDTO;
    }

    @Override
    public Visa toEntity(VisaDTO visaDTO) {
        if ( visaDTO == null ) {
            return null;
        }

        Visa visa = new Visa();

        visa.setCreatedBy( visaDTO.getCreatedBy() );
        visa.setCreatedDate( visaDTO.getCreatedDate() );
        visa.setLastModifiedBy( visaDTO.getLastModifiedBy() );
        visa.setLastModifiedDate( visaDTO.getLastModifiedDate() );
        visa.setDeleted( visaDTO.isDeleted() );
        visa.setId( visaDTO.getId() );
        visa.setCode( visaDTO.getCode() );
        visa.setLibelle( visaDTO.getLibelle() );
        Set<TypeVisa> set = visaDTO.getTypeVisas();
        if ( set != null ) {
            visa.setTypeVisas( new HashSet<TypeVisa>( set ) );
        }

        return visa;
    }
}
