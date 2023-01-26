package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Corps;
import bf.mfptps.appgestionsconges.service.dto.CorpsDTO;
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
public class CorpsMapperImpl implements CorpsMapper {

    @Override
    public List<Corps> toEntity(List<CorpsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Corps> list = new ArrayList<Corps>( dtoList.size() );
        for ( CorpsDTO corpsDTO : dtoList ) {
            list.add( toEntity( corpsDTO ) );
        }

        return list;
    }

    @Override
    public List<CorpsDTO> toDto(List<Corps> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CorpsDTO> list = new ArrayList<CorpsDTO>( entityList.size() );
        for ( Corps corps : entityList ) {
            list.add( toDto( corps ) );
        }

        return list;
    }

    @Override
    public CorpsDTO toDto(Corps corps) {
        if ( corps == null ) {
            return null;
        }

        CorpsDTO corpsDTO = new CorpsDTO();

        corpsDTO.setCreatedBy( corps.getCreatedBy() );
        corpsDTO.setCreatedDate( corps.getCreatedDate() );
        corpsDTO.setLastModifiedBy( corps.getLastModifiedBy() );
        corpsDTO.setLastModifiedDate( corps.getLastModifiedDate() );
        corpsDTO.setDeleted( corps.isDeleted() );
        corpsDTO.setId( corps.getId() );
        corpsDTO.setCodeCorps( corps.getCodeCorps() );
        corpsDTO.setLibelleCorps( corps.getLibelleCorps() );

        return corpsDTO;
    }

    @Override
    public Corps toEntity(CorpsDTO corpsDTO) {
        if ( corpsDTO == null ) {
            return null;
        }

        Corps corps = new Corps();

        corps.setCreatedBy( corpsDTO.getCreatedBy() );
        corps.setCreatedDate( corpsDTO.getCreatedDate() );
        corps.setLastModifiedBy( corpsDTO.getLastModifiedBy() );
        corps.setLastModifiedDate( corpsDTO.getLastModifiedDate() );
        corps.setDeleted( corpsDTO.isDeleted() );
        corps.setId( corpsDTO.getId() );
        corps.setCodeCorps( corpsDTO.getCodeCorps() );
        corps.setLibelleCorps( corpsDTO.getLibelleCorps() );

        return corps;
    }
}
