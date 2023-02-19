package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotifAbsenceRepository extends JpaRepository<MotifAbsence, Long>, JpaSpecificationExecutor<MotifAbsence> {

}
