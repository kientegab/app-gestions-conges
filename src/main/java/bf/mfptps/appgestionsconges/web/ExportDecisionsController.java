/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.ExportDecisionsService;
import bf.mfptps.appgestionsconges.web.exceptions.BadRequestAlertException;
import bf.mfptps.appgestionsconges.web.vm.DecisionCAVM;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/export-decisions")
public class ExportDecisionsController {

    private static final String ENTITY_NAME = "ExportDecisionCA";

    private final ExportDecisionsService exportDecisionsService;

    public ExportDecisionsController(ExportDecisionsService exportDecisionsService) {
        this.exportDecisionsService = exportDecisionsService;
    }

    /**
     *
     * @param response
     * @param dcavm
     * @throws IOException
     * @throws Exception
     */
    @PostMapping(value = "/conge-administratif")
    public void exporterDecisionCongeAdministratif(HttpServletResponse response, @RequestBody DecisionCAVM dcavm) throws IOException, Exception {
        log.debug("Export de la décision de congé admin, structure : {}", dcavm.getStructureId());
        if (dcavm.getStructureId() == null) {
            throw new BadRequestAlertException("Structure du traiteur non renseignée.", ENTITY_NAME, "idnull");
        }
        if (dcavm.getConcerneStructureId() == null) {
            throw new BadRequestAlertException("Structure des demandeurs non renseignée.", ENTITY_NAME, "idnull");
        }
        if (dcavm.getAnnee() == null) {
            throw new BadRequestAlertException("Année non renseignée.", ENTITY_NAME, "idnull");
        }
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"DECISION_CONGE_ANNUEL_" + dcavm.getConcerneStructureId() + ".pdf" + "\""));
        OutputStream outStream = response.getOutputStream();
        exportDecisionsService.printCongeAdministratif(dcavm.getStructureId(), dcavm.getConcerneStructureId(), dcavm.getAnnee(), outStream);
    }
}
