/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.MotifAbsenceService;
import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
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
//@Slf4j
@RestController
@RequestMapping("/api")
public class MotifAbsenceController {

    private final Logger log = LoggerFactory.getLogger(MotifAbsenceController.class);
    private static final String ENTITY_NAME = "motifAbsence";

    @Value("${application.name}")
    private String applicationName;

    private final MotifAbsenceService motifAbsenceService;

    public MotifAbsenceController(MotifAbsenceService motifAbsenceService) {
        this.motifAbsenceService = motifAbsenceService;
    }

    /**
     *
     * @param motifAbsenceDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/motifAbsence")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<MotifAbsenceDTO> create(@Valid @RequestBody MotifAbsenceDTO motifAbsenceDTO) throws URISyntaxException {
        log.debug("Création de motifAbsence : {}", motifAbsenceDTO);
        MotifAbsenceDTO result = motifAbsenceService.create(motifAbsenceDTO);
        return ResponseEntity.created(new URI("/api/motifAbsence/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/motifAbsence")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<MotifAbsenceDTO> update(@Valid @RequestBody MotifAbsenceDTO motifAbsenceDTO) throws URISyntaxException {
        log.debug("Mis à jour de motifAbsence : {}", motifAbsenceDTO);
        if (motifAbsenceDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        MotifAbsenceDTO result = motifAbsenceService.update(motifAbsenceDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, motifAbsenceDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/motifAbsence/{id}")
    public ResponseEntity<MotifAbsenceDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un motifAbsence : {}", id);
        Optional<MotifAbsenceDTO> result = motifAbsenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/motifAbsence")
    public ResponseEntity<List<MotifAbsenceDTO>> findAll(Pageable pageable) {
        Page<MotifAbsenceDTO> result = motifAbsenceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
