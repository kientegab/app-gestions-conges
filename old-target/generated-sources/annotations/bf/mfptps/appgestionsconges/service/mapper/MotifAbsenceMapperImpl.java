package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
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
public class MotifAbsenceMapperImpl implements MotifAbsenceMapper {

    @Override
    public List<MotifAbsence> toEntity(List<MotifAbsenceDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MotifAbsence> list = new ArrayList<MotifAbsence>( dtoList.size() );
        for ( MotifAbsenceDTO motifAbsenceDTO : dtoList ) {
            list.add( toEntity( motifAbsenceDTO ) );
        }

        return list;
    }

    @Override
    public List<MotifAbsenceDTO> toDto(List<MotifAbsence> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MotifAbsenceDTO> list = new ArrayList<MotifAbsenceDTO>( entityList.size() );
        for ( MotifAbsence motifAbsence : entityList ) {
            list.add( toDto( motifAbsence ) );
        }

        return list;
    }

    @Override
    public MotifAbsenceDTO toDto(MotifAbsence motifAbsence) {
        if ( motifAbsence == null ) {
            return null;
        }

        MotifAbsenceDTO motifAbsenceDTO = new MotifAbsenceDTO();

        motifAbsenceDTO.setId( motifAbsence.getId() );
        motifAbsenceDTO.setLibelle( motifAbsence.getLibelle() );

        return motifAbsenceDTO;
    }

    @Override
    public MotifAbsence toEntity(MotifAbsenceDTO motifAbsenceDTO) {
        if ( motifAbsenceDTO == null ) {
            return null;
        }

        MotifAbsence motifAbsence = new MotifAbsence();

        motifAbsence.setId( motifAbsenceDTO.getId() );
        motifAbsence.setLibelle( motifAbsenceDTO.getLibelle() );

        return motifAbsence;
    }
}
