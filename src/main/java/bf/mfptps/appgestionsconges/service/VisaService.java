package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.Visa;
import bf.mfptps.appgestionsconges.service.dto.VisaDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author TEGUERA
 */
public interface VisaService {

    VisaDTO create(VisaDTO visaDTO);

    VisaDTO update(VisaDTO visaDTO);

    Optional<VisaDTO> findOne(Long id);

    Visa findVisaById(Long id);

    Page<VisaDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
