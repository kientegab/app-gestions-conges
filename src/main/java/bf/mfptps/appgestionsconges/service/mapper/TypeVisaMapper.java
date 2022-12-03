package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA
 */
@Mapper(componentModel = "spring")
public interface TypeVisaMapper extends EntityMapper<TypeVisaDTO, TypeVisa> {

    TypeVisaDTO toDto(TypeVisa typeVisa);

    TypeVisa toEntity(TypeVisaDTO typeVisaDTO);

//    default TypeVisa fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        TypeVisa typeVisa = new TypeVisa();
//        typeVisa.setId(id);
//        return typeVisa;
//    }
}
