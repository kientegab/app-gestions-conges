/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.AmpliationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author HEBIE
 */
public interface AmpliationService {

    AmpliationDTO create(AmpliationDTO ampliationDTO);

    AmpliationDTO update(AmpliationDTO ampliationDTO);

    Optional<AmpliationDTO> findOne(long id);

    Page<AmpliationDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
