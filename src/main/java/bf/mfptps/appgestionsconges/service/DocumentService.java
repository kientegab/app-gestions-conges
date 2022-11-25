package bf.mfptps.appgestionsconges.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;

public interface DocumentService {

	DocumentDTO create(String numeroDemande, MultipartFile fichier);

	DocumentDTO update(String numeroDemande, MultipartFile fichier);

    Optional<Document> findByReference(String ref);

    Optional<Document> findById(Long id);

    Page<DocumentDTO> findAll(Pageable pageable);

    void delete(Long reference);

}
