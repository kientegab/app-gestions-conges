package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bf.mfptps.appgestionsconges.entities.Acte;

public interface ActeRepository extends JpaRepository<Acte, Long>, JpaSpecificationExecutor<Acte> {

	Optional<Acte> findByReference(String reference);
}