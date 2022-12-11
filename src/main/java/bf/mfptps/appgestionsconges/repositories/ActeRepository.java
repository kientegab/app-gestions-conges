package bf.mfptps.appgestionsconges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bf.mfptps.appgestionsconges.entities.Acte;

public interface ActeRepository extends JpaRepository<Acte, Long>, JpaSpecificationExecutor<Acte> {
}
