/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.dto.DemandeElaborationDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface DemandeElaborationMapper {

    DemandeElaborationDTO toDTO(Demande demande);

    Demande toEntity(DemandeElaborationDTO dto);

}
