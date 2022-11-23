/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Ampliation;
import bf.mfptps.appgestionsconges.service.dto.AmpliationDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author HEBIE
 */
@Mapper(componentModel = "spring")
public interface AmpliationMapper extends EntityMapper<AmpliationDTO, Ampliation> {

    AmpliationDTO toDto(Ampliation ampliation);

    Ampliation toEntity(AmpliationDTO ampliationDTO);

    default Ampliation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ampliation ampliation = new Ampliation();
        ampliation.setId(id);
        return ampliation;
    }
}
