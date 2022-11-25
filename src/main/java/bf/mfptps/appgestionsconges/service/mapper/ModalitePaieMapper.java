/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.ModalitePaie;
import bf.mfptps.appgestionsconges.service.dto.ModalitePaieDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author HEBIE
 */
@Mapper(componentModel = "spring")
public interface ModalitePaieMapper extends EntityMapper<ModalitePaieDTO, ModalitePaie> {

    ModalitePaieDTO toDto(ModalitePaie modalitePaie);

    ModalitePaie toEntity(ModalitePaieDTO modalitePaieDTO);

    default ModalitePaie fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModalitePaie modalitePaie = new ModalitePaie();
        modalitePaie.setId(id);
        return modalitePaie;
    }
}
