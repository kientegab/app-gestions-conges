package bf.mfptps.appgestionsconges.service.mapper;

import org.mapstruct.Mapper;

import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.service.dto.TypeActeDTO;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface TypeActeMapper extends EntityMapper<TypeActeDTO, TypeActe> {

    TypeActeDTO toDto(TypeActe typeActe);

    TypeActe toEntity(TypeActeDTO typeActeDTO);


}
