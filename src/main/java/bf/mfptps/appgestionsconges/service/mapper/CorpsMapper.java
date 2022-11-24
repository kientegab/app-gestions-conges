/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Corps;
import bf.mfptps.appgestionsconges.service.dto.CorpsDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author HEBIE
 */
@Mapper(componentModel = "spring")
public interface CorpsMapper extends EntityMapper<CorpsDTO, Corps> {

    CorpsDTO toDto(Corps corps);

    Corps toEntity(CorpsDTO corpsDTO);

    default Corps fromId(Long id) {
        if (id == null) {
            return null;
        }
        Corps corps = new Corps();
        corps.setId(id);
        return corps;
    }
}
