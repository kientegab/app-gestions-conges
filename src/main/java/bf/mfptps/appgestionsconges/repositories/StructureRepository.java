package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Profile;
import bf.mfptps.appgestionsconges.entities.Structure;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface StructureRepository extends JpaRepository<Structure, Long>, JpaSpecificationExecutor<Structure> {

    List<Structure> findByParentId(Long id);

}
