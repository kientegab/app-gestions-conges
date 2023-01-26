package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Avis;
import bf.mfptps.appgestionsconges.service.dto.AvisDTO;
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
public class AvisMapperImpl implements AvisMapper {

    @Override
    public List<Avis> toEntity(List<AvisDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Avis> list = new ArrayList<Avis>( dtoList.size() );
        for ( AvisDTO avisDTO : dtoList ) {
            list.add( toEntity( avisDTO ) );
        }

        return list;
    }

    @Override
    public List<AvisDTO> toDto(List<Avis> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AvisDTO> list = new ArrayList<AvisDTO>( entityList.size() );
        for ( Avis avis : entityList ) {
            list.add( toDto( avis ) );
        }

        return list;
    }

    @Override
    public AvisDTO toDto(Avis avis) {
        if ( avis == null ) {
            return null;
        }

        AvisDTO avisDTO = new AvisDTO();

        avisDTO.setCreatedBy( avis.getCreatedBy() );
        avisDTO.setCreatedDate( avis.getCreatedDate() );
        avisDTO.setLastModifiedBy( avis.getLastModifiedBy() );
        avisDTO.setLastModifiedDate( avis.getLastModifiedDate() );
        avisDTO.setDeleted( avis.isDeleted() );
        avisDTO.setId( avis.getId() );
        avisDTO.setAvisDRH( avis.getAvisDRH() );
        avisDTO.setAvisSG( avis.getAvisSG() );
        avisDTO.setAvisSH( avis.getAvisSH() );
        avisDTO.setAvisDG( avis.getAvisDG() );
        avisDTO.setDemande( avis.getDemande() );

        return avisDTO;
    }

    @Override
    public Avis toEntity(AvisDTO avisDTO) {
        if ( avisDTO == null ) {
            return null;
        }

        Avis avis = new Avis();

        avis.setCreatedBy( avisDTO.getCreatedBy() );
        avis.setCreatedDate( avisDTO.getCreatedDate() );
        avis.setLastModifiedBy( avisDTO.getLastModifiedBy() );
        avis.setLastModifiedDate( avisDTO.getLastModifiedDate() );
        avis.setDeleted( avisDTO.isDeleted() );
        avis.setId( avisDTO.getId() );
        avis.setAvisDRH( avisDTO.getAvisDRH() );
        avis.setAvisSG( avisDTO.getAvisSG() );
        avis.setAvisSH( avisDTO.getAvisSH() );
        avis.setAvisDG( avisDTO.getAvisDG() );
        avis.setDemande( avisDTO.getDemande() );

        return avis;
    }
}
