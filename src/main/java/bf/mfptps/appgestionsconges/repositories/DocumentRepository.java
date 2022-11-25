package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bf.mfptps.appgestionsconges.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {
	Optional<Document> findByReference(String reference);
}
