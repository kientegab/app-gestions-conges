/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.ModalitePaieDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author HEBIE
 */
public interface ModalitePaieService {

    ModalitePaieDTO create(ModalitePaieDTO modalitePaieDTO);

    ModalitePaieDTO update(ModalitePaieDTO modalitePaieDTO);

    Optional<ModalitePaieDTO> findOne(long id);

    Page<ModalitePaieDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
