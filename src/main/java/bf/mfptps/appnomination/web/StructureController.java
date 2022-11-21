package bf.mfptps.appnomination.web;

import bf.mfptps.appnomination.entities.Structure;
import bf.mfptps.appnomination.service.MinistereStructureService;
import bf.mfptps.appnomination.service.StructureService;
import bf.mfptps.appnomination.service.dto.StructureDTO;
import bf.mfptps.appnomination.utils.HeaderUtil;
import bf.mfptps.appnomination.utils.PaginationUtil;
import bf.mfptps.appnomination.utils.ResponseUtil;
import bf.mfptps.appnomination.web.exceptions.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Expose tous les services qui concernent l'entite Structure
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@RestController
@RequestMapping(path = "/api")
public class StructureController {

    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "structure";

    @Value("${application.name}")
    private String applicationName;

    /**
     *
     */
    private final StructureService structureService;

    private final MinistereStructureService ministereStructureService;

    public StructureController(StructureService structureService,
            MinistereStructureService ministereStructureService) {
        this.structureService = structureService;
        this.ministereStructureService = ministereStructureService;
    }

    /**
     * Access granted to ADMIN
     *
     * @param structure
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = "/structures")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Structure> create(@Valid @RequestBody StructureDTO structure) throws URISyntaxException {
        log.debug("Création de la structure : {}", structure);
        Structure response = structureService.create(structure);
        return ResponseEntity.created(new URI("/api/structures/" + response.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, response.getId().toString()))
                .body(response);
    }

    @DeleteMapping(path = "/structures/{id}")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression d'une structure : {}", id);
        structureService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * Access granted to ADMIN ...
     *
     * @param structure
     * @return
     * @throws URISyntaxException
     */
    @PutMapping(path = "/structures")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Structure> updateStructure(@Valid @RequestBody Structure structure) throws URISyntaxException {
        log.debug("Mis à jour d'une structure : {}", structure);
        if (structure.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Structure result = structureService.update(structure);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structure.getId().toString()))
                .body(result);
    }

    @GetMapping(path = "/structures")
    public ResponseEntity<List<StructureDTO>> findAllStructure(Pageable pageable) {
        Page<StructureDTO> structure = ministereStructureService.findAllBeta(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
        return ResponseEntity.ok().headers(headers).body(structure.getContent());
    }

    /**
     *
     * @param id : id of ministere
     * @return
     */
    @GetMapping(path = "/structures/ministere/{id}")
    public ResponseEntity<List<StructureDTO>> findAllStructureByMinistere(@PathVariable Long id, Pageable pageable) {
        Page<StructureDTO> structure = ministereStructureService.findAllStructureByMinistere(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
        return ResponseEntity.ok().headers(headers).body(structure.getContent());
    }

    @GetMapping(path = "/structures/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable Long id) {
        log.debug("Consultation d une structure : {}", id);
        Optional<Structure> structureFound = structureService.get(id);
        return ResponseUtil.wrapOrNotFound(structureFound);
    }

}
