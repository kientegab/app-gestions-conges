package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.service.DocumentService;
import bf.mfptps.appgestionsconges.service.dto.DocumentDTO;
import bf.mfptps.appgestionsconges.utils.HeaderUtil;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author TEGUERA
 */
@RestController
@RequestMapping(path = "/api")
public class DocumentController {

    private final Logger log = LoggerFactory.getLogger(DocumentController.class);
    private static final String ENTITY_NAME = "document";

    @Value("${application.name}")
    private String applicationName;

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(path = "/documents{numeroDemande}")
    public ResponseEntity<DocumentDTO> create(@PathVariable("numeroDemande") String  numeroDemande, @RequestPart("file") MultipartFile file) throws URISyntaxException {
        log.debug("Création du Document pour la demande : {}", numeroDemande);
        DocumentDTO document = documentService.create(numeroDemande, file);
        return ResponseEntity.created(new URI("/api/documents/" + document.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, document.getId().toString()))
                .body(document);
    }


    @PutMapping(path = "/documents{reference}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable(name = "reference") String reference, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        log.debug("Mis à jour du Document : {}", reference);
        if (!StringUtils.hasText(reference)) {
            throw new BadRequestAlertException("Reference invalide", ENTITY_NAME, "idnull");
        }
        DocumentDTO result = documentService.update(reference, file);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping(path = "/documents/{reference}")
    public ResponseEntity<Document> getDocument(@PathVariable(name = "reference") String reference) {
        log.debug("Consultation du Document : {}", reference);
        Optional<Document> document = documentService.findByReference(reference);
        return ResponseUtil.wrapOrNotFound(document);
    }

    @GetMapping(path = "/documents")
    public ResponseEntity<List<DocumentDTO>> findAllDocuments(Pageable pageable) {
        Page<DocumentDTO> minsiteres = documentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), minsiteres);
        return ResponseEntity.ok().headers(headers).body(minsiteres.getContent());
    }

    @DeleteMapping(path = "/documents/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression du Document : {}", id);
        documentService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
