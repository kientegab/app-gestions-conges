/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appnomination.repositories;

import bf.mfptps.appnomination.entities.Notification;
import bf.mfptps.appnomination.entities.Privilege;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bieve
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege> {

    @Query("SELECT u FROM Privilege u")
    Stream<Privilege> streamAll();

}
