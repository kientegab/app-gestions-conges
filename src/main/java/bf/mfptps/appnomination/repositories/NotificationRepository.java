/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bf.mfptps.appnomination.repositories;

import bf.mfptps.appnomination.entities.Notification;
import bf.mfptps.appnomination.entities.NotificationAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
public interface NotificationRepository extends JpaRepository<Notification, Long>,JpaSpecificationExecutor<Notification> {

    boolean existsByObjet(String objet);
}
