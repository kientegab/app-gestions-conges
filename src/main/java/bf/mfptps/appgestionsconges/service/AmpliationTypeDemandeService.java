package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.AmpliationTypeDemandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AmpliationTypeDemandeService {

    AmpliationTypeDemandeDTO create(AmpliationTypeDemandeDTO ampliationTypeDemandeDTO);

    AmpliationTypeDemandeDTO update(AmpliationTypeDemandeDTO ampliationTypeDemandeDTO);

    Optional<AmpliationTypeDemandeDTO> findOne(Long id);

    Page<AmpliationTypeDemandeDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
