package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisaRepository extends JpaRepository<Visa, Long>, JpaSpecificationExecutor<Visa> {
}
