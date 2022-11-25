package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.AvisDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */
public interface AvisService {

    AvisDTO create(AvisDTO avisDTO);

    AvisDTO update(AvisDTO avisDTO);

    Optional<AvisDTO> findOne(Long id);

    Page<AvisDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
