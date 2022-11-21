/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Privilege;
import bf.mfptps.appgestionsconges.entities.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author bieve
 */
public interface ProfileRepository extends JpaRepository<Profile, Long>, JpaSpecificationExecutor<Profile> {

    Optional<Profile> findByName(String name);

    @EntityGraph(attributePaths = "privileges")
    Optional<Profile> findOneWithPrivilegesByNameIgnoreCase(String name);

    @Modifying
    @Query(value = "DELETE FROM AGENT_PROFILE WHERE PROFILE_ID = :id", nativeQuery = true)
    int deleteProfileFromAgentAssociation(@Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM PROFILE_PRIVILEGE WHERE PROFILE_ID = :id", nativeQuery = true)
    int deleteAssociatePrivilege(@Param("id") Long id);

    @Query(value = "SELECT pro.* FROM profile pro, agent_profile ap, agent ag "
            + "WHERE ag.id = ap.agent_id AND ag.matricule = :matricule "
            + "AND ap.profile_id = pro.id", nativeQuery = true)
    List<Profile> findUserProfiles(String matricule);

}
