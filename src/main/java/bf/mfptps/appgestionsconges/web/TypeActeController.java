/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.service.TypeActeService;
import bf.mfptps.appgestionsconges.service.dto.TypeActeDTO;
import bf.mfptps.appgestionsconges.service.mapper.TypeActeMapper;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author TEGUERA
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/typeActes")
public class TypeActeController {

    private static final String ENTITY_NAME = "typeActe";

    @Value("${application.name}")
    private String applicationName;

    private final TypeActeService typeActeService;
    private final TypeActeMapper typeActeMapper;

    public TypeActeController(TypeActeService typeActeService, TypeActeMapper typeActeMapper) {
        this.typeActeService = typeActeService;
		this.typeActeMapper = typeActeMapper;
    }

    /**
     *
     * @param typeActeDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<TypeActeDTO> create(@Valid @RequestParam(value = "typeacte") String typeActeJson, @RequestParam(value = "file") MultipartFile templateFile) throws URISyntaxException {
        log.debug("Création de typeActe : {}", typeActeJson);
        ObjectMapper mapper = new ObjectMapper();
        TypeActeDTO typeActeDTO;
        try {
        	typeActeDTO = mapper.readValue(typeActeJson, TypeActeDTO.class);
        } catch (Exception e) {
                log.error("Failed to parse string to ACTE DTO", e);
            throw new CustomException("Echec lors du formatage des donnees du formulaire");
        }
        
        TypeActe result = typeActeService.save(typeActeMapper.toEntity(typeActeDTO),  templateFile);
        return ResponseEntity.created(new URI("/api/typeActes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(typeActeMapper.toDto(result));
    }


    @PutMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<TypeActeDTO> update(@Valid @RequestParam(value = "typeacte") String typeActeJson, @RequestParam(value = "file", required = true) MultipartFile templateFile) throws URISyntaxException {
        log.debug("Mis à jour de typeActe : {}", typeActeJson);
        
        ObjectMapper mapper = new ObjectMapper();
        TypeActeDTO typeActeDTO;
        try {
        	typeActeDTO = mapper.readValue(typeActeJson, TypeActeDTO.class);
        } catch (Exception e) {
                log.error("Failed to parse string to ACTE DTO", e);
            throw new CustomException("Echec lors du formatage des donnees du formulaire");
        }
        
        if (typeActeDTO==null || typeActeDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        
        TypeActe result = typeActeService.update(typeActeMapper.toEntity(typeActeDTO), templateFile);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeActeDTO.getId().toString()))
                .body(typeActeMapper.toDto(result));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TypeActe> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un typeActe : {}", id);
        Optional<TypeActe> result = typeActeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TypeActe>> findAll(Pageable pageable) {
        Page<TypeActe> result = typeActeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
