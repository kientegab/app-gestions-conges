/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.AmpliationService;
import bf.mfptps.appgestionsconges.service.dto.AmpliationDTO;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author HEBIE
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/ampliation")
public class AmpliationController {

    private static final String ENTITY_NAME = "ampliation";

    @Value("${application.name}")
    private String applicationName;

    private final AmpliationService ampliationService;

    public AmpliationController(AmpliationService ampliationService) {
        this.ampliationService = ampliationService;
    }

    /**
     *
     * @param ampliationDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AmpliationDTO> create(@Valid @RequestBody AmpliationDTO ampliationDTO) throws URISyntaxException {
        log.debug("Création de ampliation : {}", ampliationDTO);
        AmpliationDTO result = ampliationService.create(ampliationDTO);
        return ResponseEntity.created(new URI("/api/ampliation/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param type
     * @return
     * @throws URISyntaxException
     */
    @PutMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AmpliationDTO> update(@Valid @RequestBody AmpliationDTO ampliationDTO) throws URISyntaxException {
        log.debug("Mis à jour de ampliation : {}", ampliationDTO);
        if (ampliationDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        AmpliationDTO result = ampliationService.update(ampliationDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ampliationDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<AmpliationDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un ampliation : {}", id);
        Optional<AmpliationDTO> result = ampliationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AmpliationDTO>> findAll(Pageable pageable) {
        Page<AmpliationDTO> result = ampliationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
