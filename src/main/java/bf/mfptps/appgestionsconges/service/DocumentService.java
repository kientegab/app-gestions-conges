package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */
public interface DocumentService {

    DocumentDTO create(DocumentDTO documentDTO);

    DocumentDTO update(DocumentDTO documentDTO);

    Optional<DocumentDTO> findOne(Long id);

    Page<DocumentDTO> findAll(Pageable pageable);

    void deleteOne(Long id);
}
