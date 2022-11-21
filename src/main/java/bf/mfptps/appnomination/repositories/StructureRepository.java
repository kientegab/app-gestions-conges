package bf.mfptps.appnomination.repositories;

import bf.mfptps.appnomination.entities.Profile;
import bf.mfptps.appnomination.entities.Structure;
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
