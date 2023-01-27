package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.service.dto.MinistereDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T11:11:04+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
@Component
public class MinistereMapperImpl implements MinistereMapper {

    @Override
    public MinistereDTO toDto(Ministere ministere) {
        if ( ministere == null ) {
            return null;
        }

        MinistereDTO ministereDTO = new MinistereDTO();

        ministereDTO.setCode( ministere.getCode() );
        ministereDTO.setLibelle( ministere.getLibelle() );
        ministereDTO.setSigle( ministere.getSigle() );

        return ministereDTO;
    }

    @Override
    public Ministere toEntity(MinistereDTO ministereDTO) {
        if ( ministereDTO == null ) {
            return null;
        }

        Ministere ministere = new Ministere();

        ministere.setCode( ministereDTO.getCode() );
        ministere.setLibelle( ministereDTO.getLibelle() );
        ministere.setSigle( ministereDTO.getSigle() );

        return ministere;
    }
}
