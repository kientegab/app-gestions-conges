/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.AvisService;
import bf.mfptps.appgestionsconges.service.dto.AvisDTO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author TEGUERA
 */
@RestController
@RequestMapping("/api")
public class AvisController {

    private final Logger log = LoggerFactory.getLogger(AvisController.class);
    private static final String ENTITY_NAME = "avis";

    @Value("${application.name}")
    private String applicationName;

    private final AvisService avisService;

    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    /**
     *
     * @param avisDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/avis")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AvisDTO> create(@Valid @RequestBody AvisDTO avisDTO) throws URISyntaxException {
        log.debug("Création de avis : {}", avisDTO);
        AvisDTO result = avisService.create(avisDTO);
        return ResponseEntity.created(new URI("/api/avis/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/avis")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<AvisDTO> update(@Valid @RequestBody AvisDTO avisDTO) throws URISyntaxException {
        log.debug("Mis à jour de avis : {}", avisDTO);
        if (avisDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        AvisDTO result = avisService.update(avisDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, avisDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/avis/{id}")
    public ResponseEntity<AvisDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un avis : {}", id);
        Optional<AvisDTO> result = avisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/avis")
    public ResponseEntity<List<AvisDTO>> findAll(Pageable pageable) {
        Page<AvisDTO> result = avisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
