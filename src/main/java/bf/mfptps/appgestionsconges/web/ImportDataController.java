/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web;

import bf.mfptps.appgestionsconges.service.CustomException;
import bf.mfptps.appgestionsconges.service.ImportDataService;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.utils.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/import-data")
public class ImportDataController {

    private final ImportDataService importDataService;

    public ImportDataController(ImportDataService importDataService) {
        this.importDataService = importDataService;
    }

    /**
     * IMPORTE UN FICHIER EXCEL OU CSV CONTENANT LES DONNEES CORPS ISSUES DE
     * SIGASPE
     *
     * @param file
     * @return
     */
    @PostMapping(path = "/corps")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<ResponseMessage> importerCorps(@RequestPart("file") MultipartFile file) {
        log.info("IMPORT DE DONNEES CORPS : {} ", file.getOriginalFilename() + "\nProcessus en cours...");
        //evaluer la duree de traitement
        Long start = System.currentTimeMillis(), duration = 0L;

        if (!AppUtil.TYPE_EXCEL.equals(file.getContentType()) && !AppUtil.TYPE_CSV.equals(file.getContentType())) {
            throw new CustomException("Veuillez importer un fichier excel ou .csv SVP.");
        }
        String message = "";

        try {
            importDataService.importerCorps(file);
            message = "Import et synchronisation de données effectués avec succès ! (DataSource : " + file.getOriginalFilename() + ")";
            System.out.println("\n===============================\nOPERATION D'IMPORT TERMINEE ! :\nTraitement effectué en " + (duration = (System.currentTimeMillis() - start)) + " mses, soit " + (duration / 1000) + " sec.\n");

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(), message, "Aucun détail"));
        } catch (Exception e) {
            message = "Impossible de traiter le fichier : " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(HttpStatus.EXPECTATION_FAILED.value(), message, e.getMessage()));
        }

    }

    /**
     * IMPORTE UN FICHIER EXCEL OU CSV CONTENANT LES DONNEES AGENTS ISSUES DE
     * SIGASPE
     *
     * @param file
     * @return
     */
    @PostMapping(path = "/agents")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<ResponseMessage> importerAgents(@RequestPart("file") MultipartFile file) {
        log.info("IMPORT DE DONNEES AGENTS : {} ", file.getOriginalFilename() + "\nProcessus en cours...");
        //evaluer la duree de traitement
        Long start = System.currentTimeMillis(), duration = 0L;

        if (!AppUtil.TYPE_EXCEL.equals(file.getContentType()) && !AppUtil.TYPE_CSV.equals(file.getContentType())) {
            throw new CustomException("Veuillez importer un fichier excel ou .csv SVP.");
        }
        String message = "";

        try {
            importDataService.importerAgents(file);
            message = "Import et synchronisation de données effectués avec succès ! (DataSource : " + file.getOriginalFilename() + ")";
            System.out.println("\n===============================\nOPERATION D'IMPORT TERMINEE ! :\nTraitement effectué en " + (duration = (System.currentTimeMillis() - start)) + " mses, soit " + (duration / 1000) + "sec.\n");
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(), message, "Aucun détail"));
        } catch (Exception e) {
            message = "Impossible de traiter le fichier : " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(HttpStatus.EXPECTATION_FAILED.value(), message, e.getMessage()));
        }

    }

}
