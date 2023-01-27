/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author HEBIE
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/actes")
public class ActeController {

    private static final String ENTITY_NAME = "acte";

    @Value("${application.name}")
    private String applicationName;

    private final ActeService acteService;

    public ActeController(ActeService acteService) {
        this.acteService = acteService;
    }

    /**
     *
     * @param articleDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<ActeDTO> create(@RequestBody ActeDTO acteDTO) throws URISyntaxException {
        log.debug("Création de acte : {}", acteDTO);
        ActeDTO result = acteService.create(acteDTO);
        return ResponseEntity.created(new URI("/api/actes/" + result.getId()))
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
    public ResponseEntity<ActeDTO> update(@RequestBody ActeDTO acteDTO) throws URISyntaxException {
        log.debug("Mis à jour de article : {}", acteDTO);
        if (acteDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        ActeDTO result = acteService.update(acteDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, acteDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Acte> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un article : {}", id);
        Optional<Acte> result = acteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Acte>> findAll(Pageable pageable) {
        Page<Acte> result = acteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
    
    @GetMapping(path = "/generate_acte")
    public ResponseEntity<Resource> generateActe(@RequestParam("referenceActe") String reference) throws FileNotFoundException {
        File result = acteService.generateActe(reference);
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(result));
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.getName() + "\"")
                .contentLength(result.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
      ///  return ResponseEntity.ok().body(result);
    }
}
