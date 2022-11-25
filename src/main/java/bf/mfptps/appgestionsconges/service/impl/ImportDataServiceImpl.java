/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.Corps;
import bf.mfptps.appgestionsconges.enums.Sexe;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.CorpsRepository;
import bf.mfptps.appgestionsconges.service.CustomException;
import bf.mfptps.appgestionsconges.service.ImportDataService;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Represente le fonctionnaire ou la personne nommé. Elle est fourni par import
 * source SIGASPE
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
public class ImportDataServiceImpl implements ImportDataService {

    private final AgentRepository agentRepository;

    private final CorpsRepository corpsRepository;

    public ImportDataServiceImpl(AgentRepository agentRepository, CorpsRepository corpsRepository) {
        this.agentRepository = agentRepository;
        this.corpsRepository = corpsRepository;
    }

    /* select a.no_matricule, a.lt_matricule, a.nm, a.pn, a.nm_jf, a.sexe,a.dt_naissance, a.lieu_naissance, a.no_cnib, b.DT_POSIT_ADM,a.TEL_MOBILE,a.EMAIL, b.CD_QUAL_AGENT, b.DT_QUAL_AGENT,
b.CD_CATEGORIE, b.CD_ECHELLE , b.CD_ECHELON , b.CD_POSIT_ADM, b.CD_GRADE, b.CD_AFFECTATION, b.CD_CORPS
from fp.c a ,  fp.agent_fp b
where a.no_matricule= b.no_matricule
--and b.cd_categorie='A'
--and b.cd_echelle='1'
--and b.cd_echelon = '03'
and a.no_cnib Is not null
--and  a.DT_NAISSANCE = '07/07/1993'
--and a.no_matricule='371270'
fetch first 100 rows only
; */
    @Override
    public void importerAgents(MultipartFile file) {
        try {
            if (AppUtil.TYPE_EXCEL.equals(file.getContentType())) {
                List<Agent> agents = this.lectureDuFichierAgent(file.getInputStream());
                this.traitementAgentExcel(agents);
            } else {
                throw new CustomException("Cette fonctionnalité est en cours d'implémentation...");
            }

        } catch (IOException ex) {
            System.out.println("Une erreur est survenue lors du traitement des données.");
        }
    }

    @Override
    public void importerCorps(MultipartFile file) {
        try {
            if (AppUtil.TYPE_EXCEL.equals(file.getContentType())) {
                List<Corps> corps = this.lectureDuFichierCorps(file.getInputStream());
                this.traitementCorpsExcel(corps);
            } else {
                throw new CustomException("Cette fonctionnalité est en cours d'implémentation...");
            }

        } catch (IOException ex) {
            System.out.println("Une erreur est survenue lors du traitement des données.");
        }
    }

    //================================= DEBUT TRAITEMENT FILE AGENT ==================================
    private void traitementAgentExcel(List<Agent> agents) {
        List<Agent> newAgents = new ArrayList<Agent>();
        List<Agent> agentsToUpdate = new ArrayList<Agent>();

        for (Agent agent : agents) {
            Agent agentFromdb = agentRepository.findOneByMatricule(agent.getMatricule()).orElse(null);
            if (agentFromdb == null) {//faire une insertion car nouvelle ligne c
                System.out.println("...................... A AJOUTER MATRICULE = " + agent.getMatricule());
                agent.setPassword(AppUtil.DEFAULT_PASSWORD);
                agent.setActif(false);
                newAgents.add(agent);
            } else {//faire une mise a jour d'une ligne en db
                System.out.println("...................... MISE A JOUR ID = " + agentFromdb.getId() + " MATRICULE = " + agent.getMatricule());
                agentFromdb.setNoCNIB(agent.getNoCNIB());
                agentFromdb.setNom(agent.getNom());
                agentFromdb.setPrenom(agent.getPrenom());
                agentFromdb.setSexe(agent.getSexe());
                agentFromdb.setNomJeuneFille(agent.getNomJeuneFille());
                agentFromdb.setDateNaissance(agent.getDateNaissance());
                agentFromdb.setLieuNaissance(agent.getLieuNaissance());
                agentFromdb.setDateRecrutement(agent.getDateRecrutement());
                agentFromdb.setTelephone(agent.getTelephone());
                agentFromdb.setEmail(agent.getEmail());
                agentFromdb.setQualite(agent.getQualite());
                agentFromdb.setDateQualite(agent.getDateQualite());
                agentFromdb.setCategorie(agent.getCategorie());
                agentFromdb.setEchelle(agent.getEchelle());
                agentFromdb.setEchellon(agent.getEchellon());
                agentFromdb.setPosition(agent.getPosition());
                agentFromdb.setGrade(agent.getGrade());
                agentFromdb.setAffectation(agent.getAffectation());
                agentFromdb.setCorps(agent.getCorps());

                agentsToUpdate.add(agentFromdb);
            }
        }

        if (!newAgents.isEmpty()) {
            System.out.println("_______________________ ici");
            agentRepository.saveAll(newAgents);
        }

        if (!agentsToUpdate.isEmpty()) {
            agentRepository.saveAll(agentsToUpdate);
        }
    }

    private List<Agent> lectureDuFichierAgent(InputStream is) throws IOException {
        List<Agent> agentsFromFile = new ArrayList<>();//liste des agents issus du fichier

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(0);//nom de la feuille (par defaut la 1ere)

        //on parcourt le fichier pour extraire chaque c
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Agent agent = new Agent();

                XSSFRow row = worksheet.getRow(index);
                agent.setMatricule("" + (int) row.getCell(0).getNumericCellValue());
                agent.setCleMatricule(row.getCell(1).getStringCellValue());
                agent.setNom(row.getCell(2).getStringCellValue());
                agent.setPrenom(row.getCell(3).getStringCellValue());
                agent.setNomJeuneFille(row.getCell(4).getStringCellValue());
                agent.setSexe(row.getCell(5).getStringCellValue() == "M" ? Sexe.MASCULIN : Sexe.FEMININ);
                //agent.setDateNaissance(row.getCell(6).getDateCellValue());
                agent.setLieuNaissance(row.getCell(7).getStringCellValue());
                agent.setNoCNIB(row.getCell(8).getStringCellValue());
                //agent.setDateRecrutement(row.getCell(9).getDateCellValue());
                agent.setTelephone(row.getCell(10).getStringCellValue());
                agent.setEmail(row.getCell(11).getStringCellValue());
                agent.setQualite(row.getCell(12).getStringCellValue());
                //agent.setDateQualite(row.getCell(13).getDateCellValue());
                agent.setCategorie(row.getCell(14).getStringCellValue());
                agent.setEchelle(row.getCell(15).getStringCellValue());
                agent.setEchellon(row.getCell(16).getStringCellValue());
                agent.setPosition(row.getCell(17).getStringCellValue());
                agent.setGrade(row.getCell(18).getStringCellValue());
                agent.setAffectation(row.getCell(19).getStringCellValue());

                Corps corps = corpsRepository.findByCodeCorps(row.getCell(20).getStringCellValue()).orElse(null);
                if (corps != null) {
                    agent.setCorps(corps);
                }

                agentsFromFile.add(agent);
            }
        }
        return agentsFromFile;
    }

    //======================================= DEBUT TRAITEMENT FILE CORPS =====================================
    private void traitementCorpsExcel(List<Corps> corps) {
        List<Corps> newCorps = new ArrayList<Corps>();
        List<Corps> corpsToUpdate = new ArrayList<Corps>();

        for (Corps c : corps) {
            Corps corpsFromdb = corpsRepository.findByCodeCorps(c.getCodeCorps()).orElse(null);
            if (corpsFromdb == null) {//faire une insertion car nouvelle ligne c
                System.out.println("...................... A AJOUTER CODE_CORPS = " + c.getCodeCorps());
                newCorps.add(c);
            } else {//faire une mise a jour d'une ligne en db
                System.out.println("...................... MISE A JOUR ID = " + corpsFromdb.getId() + " CODE = " + c.getCodeCorps());
                corpsFromdb.setLibelleCorps(c.getLibelleCorps());

                corpsToUpdate.add(corpsFromdb);

            }
        }

        if (!newCorps.isEmpty()) {
            corpsRepository.saveAll(newCorps);
        }
        if (!corpsToUpdate.isEmpty()) {
            corpsRepository.saveAll(corpsToUpdate);
        }

    }

    private List<Corps> lectureDuFichierCorps(InputStream is) throws IOException {
        List<Corps> corpsFromFile = new ArrayList<>();//liste des corps issus du fichier

        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet worksheet = workbook.getSheetAt(0);//nom de la feuille (par defaut la 1ere)

        //on parcourt le fichier pour extraire chaque c
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Corps corps = new Corps();

                XSSFRow row = worksheet.getRow(index);

                corps.setCodeCorps("" + row.getCell(0).getStringCellValue());
                corps.setLibelleCorps(row.getCell(1).getStringCellValue());

                corpsFromFile.add(corps);
            }
        }
        return corpsFromFile;
    }

}
