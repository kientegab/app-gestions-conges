package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.service.dto.TypeActeDTO;
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
public class TypeActeMapperImpl implements TypeActeMapper {

    @Override
    public List<TypeActe> toEntity(List<TypeActeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TypeActe> list = new ArrayList<TypeActe>( dtoList.size() );
        for ( TypeActeDTO typeActeDTO : dtoList ) {
            list.add( toEntity( typeActeDTO ) );
        }

        return list;
    }

    @Override
    public List<TypeActeDTO> toDto(List<TypeActe> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TypeActeDTO> list = new ArrayList<TypeActeDTO>( entityList.size() );
        for ( TypeActe typeActe : entityList ) {
            list.add( toDto( typeActe ) );
        }

        return list;
    }

    @Override
    public TypeActeDTO toDto(TypeActe typeActe) {
        if ( typeActe == null ) {
            return null;
        }

        TypeActeDTO typeActeDTO = new TypeActeDTO();

        typeActeDTO.setId( typeActe.getId() );
        typeActeDTO.setReference( typeActe.getReference() );
        typeActeDTO.setLibelle( typeActe.getLibelle() );
        typeActeDTO.setTemplateUri( typeActe.getTemplateUri() );
        typeActeDTO.setPorteActe( typeActe.getPorteActe() );

        return typeActeDTO;
    }

    @Override
    public TypeActe toEntity(TypeActeDTO typeActeDTO) {
        if ( typeActeDTO == null ) {
            return null;
        }

        TypeActe typeActe = new TypeActe();

        typeActe.setId( typeActeDTO.getId() );
        typeActe.setReference( typeActeDTO.getReference() );
        typeActe.setLibelle( typeActeDTO.getLibelle() );
        typeActe.setTemplateUri( typeActeDTO.getTemplateUri() );
        typeActe.setPorteActe( typeActeDTO.getPorteActe() );

        return typeActe;
    }
}
