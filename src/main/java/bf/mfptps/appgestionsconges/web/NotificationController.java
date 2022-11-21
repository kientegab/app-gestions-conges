/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.entities.Notification;
import bf.mfptps.appgestionsconges.entities.NotificationAgent;
import bf.mfptps.appgestionsconges.service.NotificationAgentService;
import bf.mfptps.appgestionsconges.service.NotificationService;
import bf.mfptps.appgestionsconges.service.dto.NotificationAgentDTO;
import bf.mfptps.appgestionsconges.service.dto.NotificationDTO;
import bf.mfptps.appgestionsconges.service.impl.EmailServiceImpl;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private static final String ENTITY_NAME = "notification_agent";

    @Value("${application.name}")
    private String applicationName;

    private final EmailServiceImpl emailServiceImpl;

    private final NotificationAgentService notificationAgentService;

    private final NotificationService notificationService;

    public NotificationController(EmailServiceImpl emailServiceImpl,
            NotificationAgentService notificationAgentService,
            NotificationService notificationService) {
        this.emailServiceImpl = emailServiceImpl;
        this.notificationAgentService = notificationAgentService;
        this.notificationService = notificationService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Notification> create(@Valid @RequestBody NotificationDTO notificationDTO) throws URISyntaxException {

        Notification notification = notificationService.create(notificationDTO);
        log.debug("Création d'une notification : {}", notificationDTO);
        return ResponseEntity.created(new URI("/api/notifications" + notification.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, notification.getId().toString()))
                .body(notification);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> findAll(Pageable pageable) {
        Page<NotificationDTO> notification = notificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notification);
        return ResponseEntity.ok().headers(headers).body(notification.getContent());

    }

    @GetMapping(path = "/agent/")
    public ResponseEntity<List<NotificationAgentDTO>> findAllAgent(Pageable pageable) {
        Page<NotificationAgentDTO> notificationAgent = notificationAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notificationAgent);
        return ResponseEntity.ok().headers(headers).body(notificationAgent.getContent());

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NotificationDTO> getNotificationByID(@PathVariable(name = "id") Long id) {
        log.debug("Consultation de la notification : {}", id);
        Optional<NotificationDTO> notification = notificationService.get(id);
        return ResponseUtil.wrapOrNotFound(notification);
    }

    @GetMapping(path = "/agent/{username}")
    public ResponseEntity<List<NotificationAgent>> getNotificationAgentByID(@PathVariable(name = "username") String username, Pageable pageable) {
        log.debug("Consultation de la notification : {}", username);
        Page<NotificationAgent> notificationAgent = notificationAgentService.get(username, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notificationAgent);
        return ResponseEntity.ok().headers(headers).body(notificationAgent.getContent());
    }

    @GetMapping(path = "/nonlu/{username}")
    public Long getNumberNonlu(@PathVariable(name = "username") String username) {
        log.debug("notification non lu: {}", username);
        return notificationAgentService.getNonLu(username);
    }

    /**
     * Access granted to ADMIN
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/{id}")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression de la notification : {}", id);
        notificationAgentService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

}
