/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.utils;


import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import bf.mfptps.appgestionsconges.web.vm.ManagedAgentVM;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import bf.mfptps.appgestionsconges.web.vm.ManagedAgentVM;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
public class AppUtil {

    private static InputStream logoStream;

    @Size(min = ManagedAgentVM.PASSWORD_MIN_LENGTH, max = ManagedAgentVM.PASSWORD_MAX_LENGTH)
    public static final String DEFAULT_PASSWORD = "12345678";
    public static String TYPE_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE_CSV = "application/csv";

    /**
     * TYPE OF STRUCTURE
     */
    public static final String STRUCTURE_CENTRALE = "CENTRALE";
    public static final String STRUCTURE_DECONCENTREE = "DECONCENTREE";
    public static final String STRUCTURE_RATTACHEE = "RATTACHEE";
    public static final String STRUCTURE_DE_MISSION = "DE_MISSION";
    public static final String STRUCTURE_INTERNE = "INTERNE";

    /**
     * BASIC MINISTERE, STRUCTURE AND TYPESTRUCTURE DATAS
     */
    public static final String BASIC_MINISTERE_CODE = "00-TEST";
    public static final String BASIC_MINISTERE_LABEL = "Ministere en charge de la fonction publique - TEST";
    public static final String BASIC_MINISTERE_SIGLE = "MFP - TEST";

    public static final String BASIC_STRUCTURE_LABEL = "Structure de Test";
    public static final String BASIC_STRUCTURE_SIGLE = "STRUC - TEST";
    public static final String BASIC_STRUCTURE_TELEPHONE = "00.00.00.00";
    public static final String BASIC_STRUCTURE_EMAIL = "contact.test@fp.gov.bf";
    public static final String BASIC_STRUCTURE_RESPONSABLE = "contact.test@fp.gov.bf";

    public static final String BASIC_TYPE_STRUCTURE_LIBELLE = "CENTRALE";

    /**
     * CODES TYPE DE DEMANDE
     */
    public static final String CONGE_ANNUEL = "CONGE_ANNUEL";
    public static final String CONGE_MALADIE_1_10 = "CONGE_MALADIE_1_10";
    public static final String CONGE_MALADIE_11_30 = "CONGE_MALADIE_11_30";
    public static final String CONGE_MALADIE_LONGUE_DUREE = "CONGE_MALADIE_LONGUE_DUREE";
    public static final String CONGE_MATERNITE = "CONGE_MATERNITE";
    public static final String CONGE_FSERVICE = "CONGE_FSERVICE";
    public static final String JOUISS_ANNUEL = "JOUISS_ANNUEL";
    public static final String JOUISS_EXAMEN = "JOUISS_EXAMEN";
    public static final String JOUISS_SND = "JOUISS_SND";
    public static final String AUTRE_ABSENCE = "AUTRE_ABSENCE";

    /**
     * ALL ROLE/PRIVILEGES OF USERS
     */
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String GESTIONNAIRE = "ROLE_GESTIONNAIRE";

    /**
     *
     * @return
     */
    public static InputStream getAppDefaultLogo() {
        try {
            logoStream = AppUtil.class.getResourceAsStream("/logo.png");
            return logoStream;
        } catch (Exception e) {
            throw new CustomException("Erreur lors du chargement du logo..." + e);
        }
    }

    public static double arrondirDecimal(double valeur) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        return Double.valueOf(df.format(valeur).replace(",", "."));
    }

    public static String saveUploadFileToServer(String uplaodStorage, String userStorage,
            MultipartFile file) throws Exception {

        try {
            byte[] bytes = file.getBytes();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uplaodStorage + File.separatorChar + userStorage + File.separatorChar + fileName);
            File dir = new File(path.toString());
            if (!dir.exists()) {
                dir.getParentFile().mkdirs();
            }
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            log.error("Failed to write file on server", e);
            throw new Exception("Failed to write file on server " + e.getMessage());
        }

    }

}
