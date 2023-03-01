package bf.mfptps.appgestionsconges.web;


import bf.mfptps.appgestionsconges.service.AmpliationTypeDemandeService;
import bf.mfptps.appgestionsconges.service.dto.AmpliationTypeDemandeDTO;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ZINA
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class AmpliationTypeDemandeController {

    private static final String ENTITY_NAME = "ampliationTypeDemande";

    @Value("${application.name}")
    private String applicationName;

    private final AmpliationTypeDemandeService ampliationTypeDemandeService ;

    public AmpliationTypeDemandeController(AmpliationTypeDemandeService ampliationTypeDemandeService) {
        this.ampliationTypeDemandeService = ampliationTypeDemandeService;
    }


    @PostMapping("/ampliationTypeDemandes")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AmpliationTypeDemandeDTO> create(@Valid @RequestBody AmpliationTypeDemandeDTO ampliationTypeDemandeDTO) throws URISyntaxException {
        log.debug("Création de ampliationTypeDemande : {}", ampliationTypeDemandeDTO);
        AmpliationTypeDemandeDTO result = ampliationTypeDemandeService.create(ampliationTypeDemandeDTO);
        return ResponseEntity.created(new URI("/api/ampliationTypeDemande/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/ampliationTypeDemandes")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AmpliationTypeDemandeDTO> update(@Valid @RequestBody AmpliationTypeDemandeDTO ampliationTypeDemandeDTO) throws URISyntaxException {
        log.debug("Mis à jour de ampliationTypeDemande : {}", ampliationTypeDemandeDTO);
        if (ampliationTypeDemandeDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        AmpliationTypeDemandeDTO result = ampliationTypeDemandeService.update(ampliationTypeDemandeDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ampliationTypeDemandeDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/ampliationTypeDemandes/{id}")
    public ResponseEntity<AmpliationTypeDemandeDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un ampliationTypeDemande : {}", id);
        Optional<AmpliationTypeDemandeDTO> result = ampliationTypeDemandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/ampliationTypeDemandes")
    public ResponseEntity<List<AmpliationTypeDemandeDTO>> findAll(Pageable pageable) {
        Page<AmpliationTypeDemandeDTO> result = ampliationTypeDemandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
