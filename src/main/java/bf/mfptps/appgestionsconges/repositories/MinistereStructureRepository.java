package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.entities.Structure;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface MinistereStructureRepository extends JpaRepository<MinistereStructure, Long>, JpaSpecificationExecutor<MinistereStructure> {

    Optional<MinistereStructure> findByStructureIdAndStatutIsTrue(@NotNull long structureId);

    Page<MinistereStructure> findAllByStatutIsTrue(Pageable pageable);

    @Query("SELECT ms FROM MinistereStructure ms "
            + "WHERE ms.statut IS TRUE "
            + "AND ms.ministere.code != :codeMinistere")
    Page<MinistereStructure> findAllByStatutIsTrue(String codeMinistere, Pageable pageable);

    Page<MinistereStructure> findByMinistereIdAndStatutIsTrue(long ministereId, Pageable pageable);

    @Query("SELECT ms.structure FROM MinistereStructure ms "
            + "WHERE ms.ministere.id = :ministereId "
            + "AND ms.statut IS TRUE")
    List<Structure> findAllStructuresByMinistere(long ministereId);

    @Query("SELECT ms.structure FROM MinistereStructure ms, Agent a, AgentStructure ags "
            + "WHERE a.deleted = false AND a.matricule = :matricule "
            + "AND a.id = ags.agent.id AND ags.actif = true "
            + "AND ags.structure.id = ms.structure.id AND ms.statut = true")
    Optional<Structure> findByAgentMatricule(String matricule);
}
