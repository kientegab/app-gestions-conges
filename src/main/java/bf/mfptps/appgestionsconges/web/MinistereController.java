package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.service.MinistereService;
import bf.mfptps.appgestionsconges.service.dto.MinistereDTO;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
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
 * @author Canisius <canisiushien@gmail.com>
 */
@RestController
@RequestMapping(path = "/api")
public class MinistereController {

    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "ministere";

    @Value("${application.name}")
    private String applicationName;

    private final MinistereService ministereService;

    public MinistereController(MinistereService ministereService) {
        this.ministereService = ministereService;
    }

    /**
     * Access granted to ADMIN ...
     *
     * @param ministere
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = "/ministeres")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Ministere> create(@Valid @RequestBody MinistereDTO ministere) throws URISyntaxException {
        log.debug("Création du Ministere : {}", ministere);
        Ministere min = ministereService.create(ministere);
        return ResponseEntity.created(new URI("/api/ministeres/" + min.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, min.getId().toString()))
                .body(min);
    }

    /**
     * Access granted to ADMIN ...
     *
     * @param ministere
     * @return
     * @throws URISyntaxException
     */
    @PutMapping(path = "/ministeres")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Ministere> updateMinistere(@Valid @RequestBody Ministere ministere) throws URISyntaxException {
        log.debug("Mis à jour du Ministere : {}", ministere);
        if (ministere.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Ministere result = ministereService.update(ministere);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ministere.getId().toString()))
                .body(result);
    }

    @GetMapping(path = "/ministeres/{code}")
    public ResponseEntity<Ministere> getMinistere(@PathVariable(name = "code") String code) {
        log.debug("Consultation du Ministere : {}", code);
        Optional<Ministere> ministere = ministereService.get(code);
        return ResponseUtil.wrapOrNotFound(ministere);
    }

    @GetMapping(path = "/ministeres")
    public ResponseEntity<List<Ministere>> findAllMinisteres(Pageable pageable) {
        Page<Ministere> minsiteres = ministereService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), minsiteres);
        return ResponseEntity.ok().headers(headers).body(minsiteres.getContent());
    }

    /**
     * Access granted to ADMIN
     *
     * @param id
     * @return
     */
    @DeleteMapping(path = "/ministeres/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression du Ministere : {}", id);
        ministereService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

}
