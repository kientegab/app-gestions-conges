package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */
public interface TypeVisaService {

    TypeVisaDTO create(TypeVisaDTO typeVisaDTO);

    TypeVisaDTO update(TypeVisaDTO typeVisaDTO);

    Optional<TypeVisaDTO> findOne(Long id);

    Page<TypeVisaDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
