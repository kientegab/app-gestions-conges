package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Ampliation;
import bf.mfptps.appgestionsconges.service.dto.AmpliationDTO;
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
public class AmpliationMapperImpl implements AmpliationMapper {

    @Override
    public List<Ampliation> toEntity(List<AmpliationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Ampliation> list = new ArrayList<Ampliation>( dtoList.size() );
        for ( AmpliationDTO ampliationDTO : dtoList ) {
            list.add( toEntity( ampliationDTO ) );
        }

        return list;
    }

    @Override
    public List<AmpliationDTO> toDto(List<Ampliation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AmpliationDTO> list = new ArrayList<AmpliationDTO>( entityList.size() );
        for ( Ampliation ampliation : entityList ) {
            list.add( toDto( ampliation ) );
        }

        return list;
    }

    @Override
    public AmpliationDTO toDto(Ampliation ampliation) {
        if ( ampliation == null ) {
            return null;
        }

        AmpliationDTO ampliationDTO = new AmpliationDTO();

        ampliationDTO.setCreatedBy( ampliation.getCreatedBy() );
        ampliationDTO.setCreatedDate( ampliation.getCreatedDate() );
        ampliationDTO.setLastModifiedBy( ampliation.getLastModifiedBy() );
        ampliationDTO.setLastModifiedDate( ampliation.getLastModifiedDate() );
        ampliationDTO.setDeleted( ampliation.isDeleted() );
        ampliationDTO.setId( ampliation.getId() );
        ampliationDTO.setCode( ampliation.getCode() );
        ampliationDTO.setLibelle( ampliation.getLibelle() );

        return ampliationDTO;
    }

    @Override
    public Ampliation toEntity(AmpliationDTO ampliationDTO) {
        if ( ampliationDTO == null ) {
            return null;
        }

        Ampliation ampliation = new Ampliation();

        ampliation.setCreatedBy( ampliationDTO.getCreatedBy() );
        ampliation.setCreatedDate( ampliationDTO.getCreatedDate() );
        ampliation.setLastModifiedBy( ampliationDTO.getLastModifiedBy() );
        ampliation.setLastModifiedDate( ampliationDTO.getLastModifiedDate() );
        ampliation.setDeleted( ampliationDTO.isDeleted() );
        ampliation.setId( ampliationDTO.getId() );
        ampliation.setCode( ampliationDTO.getCode() );
        ampliation.setLibelle( ampliationDTO.getLibelle() );

        return ampliation;
    }
}
