/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.mapper;

import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface MotifAbsenceMapper extends EntityMapper<MotifAbsenceDTO, MotifAbsence> {

    MotifAbsenceDTO toDto(MotifAbsence motifAbsence);

    MotifAbsence toEntity(MotifAbsenceDTO motifAbsenceDTO);

    default MotifAbsence fromId(Long id) {
        if (id == null) {
            return null;
        }
        MotifAbsence motifAbsence = new MotifAbsence();
        motifAbsence.setId(id);
        return motifAbsence;
    }
}
