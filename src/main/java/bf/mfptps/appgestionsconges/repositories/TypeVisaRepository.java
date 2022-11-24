package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.TypeVisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeVisaRepository extends JpaRepository<TypeVisa, Long>, JpaSpecificationExecutor<TypeVisa> {
}
