/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import java.io.OutputStream;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ExportDecisionsService {

    /**
     *
     * @param structureId : id de la structure du/des agents concernes par
     * l'acte
     * @param concerneStructureId : id de la structure faisant le traitement
     * @param outputStream
     */
    void printCongeAdministratif(Long structureId, long concerneStructureId, Integer annee, OutputStream outputStream);

}
