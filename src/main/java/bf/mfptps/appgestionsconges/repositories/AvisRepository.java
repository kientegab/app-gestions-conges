package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AvisRepository extends JpaRepository<Avis, Long>, JpaSpecificationExecutor<Avis> {
}
