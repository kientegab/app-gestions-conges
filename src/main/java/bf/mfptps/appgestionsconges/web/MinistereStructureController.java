package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.service.MinistereStructureService;
import bf.mfptps.appgestionsconges.utils.PaginationUtil;
import bf.mfptps.appgestionsconges.utils.ResponseUtil;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/api")
public class MinistereStructureController {

    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "ministereStructure";

    private final MinistereStructureService ministereStructureService;

    public MinistereStructureController(MinistereStructureService ministereStructureService) {
        this.ministereStructureService = ministereStructureService;
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/ministere-structures")
    public ResponseEntity<List<MinistereStructure>> findAllMinisteresStructure(Pageable pageable) {
        log.debug("Liste des ministere-structures");
        Page<MinistereStructure> minsiteresStructure = ministereStructureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), minsiteresStructure);
        return ResponseEntity.ok().headers(headers).body(minsiteresStructure.getContent());
    }

    /**
     *
     * @param id : id de la structure
     * @return
     */
    @GetMapping(path = "/ministere-structures/{id}")
    public ResponseEntity<MinistereStructure> findMinisteresStructureByStructureID(@PathVariable Long id) {
        log.debug("Consultation d'un ministere-structure : {}", id);
        Optional<MinistereStructure> structureFound = ministereStructureService.getByStructure(id);
        return ResponseUtil.wrapOrNotFound(structureFound);
    }
}
