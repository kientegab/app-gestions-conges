/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.TypeVisaService;
import bf.mfptps.appgestionsconges.service.dto.TypeVisaDTO;
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
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/typeVisas")
public class TypeVisaController {

    private static final String ENTITY_NAME = "typeVisa";

    @Value("${application.name}")
    private String applicationName;

    private final TypeVisaService typeVisaService;

    public TypeVisaController(TypeVisaService typeVisaService) {
        this.typeVisaService = typeVisaService;
    }

    /**
     *
     * @param typeVisaDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<TypeVisaDTO> create(@Valid @RequestBody TypeVisaDTO typeVisaDTO) throws URISyntaxException {
        log.debug("Création de typeVisa : {}", typeVisaDTO);
        TypeVisaDTO result = typeVisaService.create(typeVisaDTO);
        return ResponseEntity.created(new URI("/api/typeVisas/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<TypeVisaDTO> update(@Valid @RequestBody TypeVisaDTO typeVisaDTO) throws URISyntaxException {
        log.debug("Mis à jour de typeVisa : {}", typeVisaDTO);
        if (typeVisaDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        TypeVisaDTO result = typeVisaService.update(typeVisaDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeVisaDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TypeVisaDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un typeVisa : {}", id);
        Optional<TypeVisaDTO> result = typeVisaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TypeVisaDTO>> findAll(Pageable pageable) {
        Page<TypeVisaDTO> result = typeVisaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
