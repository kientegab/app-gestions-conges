/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.ArticleTypeDemandeService;
import bf.mfptps.appgestionsconges.service.dto.ArticleTypeDemandeDTO;
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
 * @author TEGUERA
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class ArticleTypeDemandeController {

    private static final String ENTITY_NAME = "articleTypeDemande";

    @Value("${application.name}")
    private String applicationName;

    private final ArticleTypeDemandeService articleTypeDemandeService;

    public ArticleTypeDemandeController(ArticleTypeDemandeService articleTypeDemandeService) {
        this.articleTypeDemandeService = articleTypeDemandeService;
    }

    /**
     *
     * @param articleTypeDemandeDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/articleTypeDemandes")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<ArticleTypeDemandeDTO> create(@Valid @RequestBody ArticleTypeDemandeDTO articleTypeDemandeDTO) throws URISyntaxException {
        log.debug("Création de articleTypeDemande : {}", articleTypeDemandeDTO);
        ArticleTypeDemandeDTO result = articleTypeDemandeService.create(articleTypeDemandeDTO);
        return ResponseEntity.created(new URI("/api/articleTypeDemande/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/articleTypeDemandes")
    //@PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<ArticleTypeDemandeDTO> update(@Valid @RequestBody ArticleTypeDemandeDTO articleTypeDemandeDTO) throws URISyntaxException {
        log.debug("Mis à jour de articleTypeDemande : {}", articleTypeDemandeDTO);
        if (articleTypeDemandeDTO.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        ArticleTypeDemandeDTO result = articleTypeDemandeService.update(articleTypeDemandeDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, articleTypeDemandeDTO.getId().toString()))
                .body(result);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/articleTypeDemandes/{id}")
    public ResponseEntity<ArticleTypeDemandeDTO> findOne(@PathVariable Long id) {
        log.debug("Consultation d'un articleTypeDemande : {}", id);
        Optional<ArticleTypeDemandeDTO> result = articleTypeDemandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/articleTypeDemandes")
    public ResponseEntity<List<ArticleTypeDemandeDTO>> findAll(Pageable pageable) {
        Page<ArticleTypeDemandeDTO> result = articleTypeDemandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), result);
        return ResponseEntity.ok().headers(headers).body(result.getContent());
    }
}
