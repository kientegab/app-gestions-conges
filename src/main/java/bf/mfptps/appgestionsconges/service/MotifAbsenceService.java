/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */

public interface MotifAbsenceService {

    MotifAbsenceDTO create(MotifAbsenceDTO motifAbsenceDTO);

    MotifAbsenceDTO update(MotifAbsenceDTO motifAbsenceDTO);

    Optional<MotifAbsenceDTO> findOne(Long id);

    Page<MotifAbsenceDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
