package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.AgentStructure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface AgentStructureRepository extends JpaRepository<AgentStructure, Long>, JpaSpecificationExecutor<AgentStructure> {

    Optional<AgentStructure> findByAgentIdAndActifTrue(Long id);

    /**
     * return the actif structure of the user.
     *
     * @param matricule : matricule or login of user
     * @param email :
     * @return
     */
    @Query("SELECT ast FROM AgentStructure ast, Agent a "
            + "WHERE ast.agent.id = a.id AND ast.actif = true "
            + "AND a.matricule = :matricule OR a.email = :email")
    AgentStructure findOneByAgentMatriculeOrAgentEmail(String matricule, String email);
    
   /* @Query("SELECT COUNT(as) FROM AgentStructure as JOIN ad.agent a Join a.structure s"
    		+ " WHERE s.sigle =:SIGLE ")
	Long countAgentStructureDemande(@Param("SIGLE") String sigle);*/

}
