/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.Notification;
import bf.mfptps.appgestionsconges.service.dto.NotificationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
public interface NotificationService {

    Notification create(NotificationDTO notificationDTO);

    Notification update(Notification notification);

    Page<Notification> get(Pageable pageable);

    Optional<NotificationDTO> get(Long id);

    Page<NotificationDTO> findAll(Pageable pageable);

    void delete(Long id);
}
