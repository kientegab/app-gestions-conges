/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisa;
import bf.mfptps.appgestionsconges.repositories.ArticleTypeDemandeRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.MinistereStructureRepository;
import bf.mfptps.appgestionsconges.repositories.StructureRepository;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeVisaRepository;
import bf.mfptps.appgestionsconges.service.CustomException;
import bf.mfptps.appgestionsconges.service.ExportDecisionsService;
import bf.mfptps.appgestionsconges.service.reportentites.AgentRE;
import bf.mfptps.appgestionsconges.service.reportentites.ArticleRE;
import bf.mfptps.appgestionsconges.service.reportentites.CongeAdminDataNextRE;
import bf.mfptps.appgestionsconges.service.reportentites.CongeAdminDataRE;
import bf.mfptps.appgestionsconges.service.reportentites.VisaRE;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class ExportDecisionsServiceImpl implements ExportDecisionsService {

    private final MinistereStructureRepository ministereStructureRepository;
    private final TypeDemandeRepository typeDemandeRepository;
    private final DemandeRepository demandeRepository;
    private final ArticleTypeDemandeRepository articleTypeDemandeRepository;
    private final TypeVisaRepository typeVisaRepository;

    public ExportDecisionsServiceImpl(MinistereStructureRepository ministereStructureRepository,
            StructureRepository structureRepository,
            TypeDemandeRepository typeDemandeRepository,
            DemandeRepository demandeRepository,
            ArticleTypeDemandeRepository articleTypeDemandeRepository,
            TypeVisaRepository typeVisaRepository) {
        this.ministereStructureRepository = ministereStructureRepository;
        this.typeDemandeRepository = typeDemandeRepository;
        this.demandeRepository = demandeRepository;
        this.articleTypeDemandeRepository = articleTypeDemandeRepository;
        this.typeVisaRepository = typeVisaRepository;
    }

    @Override
    public void printCongeAdministratif(Long structureId, long concerneStructureId, Integer annee, OutputStream outputStream) {
        try {
            //ministere et structure de tutelle
            MinistereStructure ms = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(structureId).orElseThrow(() -> new CustomException("Votre structure [" + structureId + "] n'est rattachée à aucun ministère. Veuillez contacter l'admin pour le paramétrage SVP."));
            //recherche du type CONGE ANNUEL
            TypeDemande typeDemande = typeDemandeRepository.findByCodeAndDeletedIsFalse(AppUtil.CONGE_ANNUEL).orElseThrow(() -> new CustomException("Type d'acte inexistant. Veuillez contacter l'admin pour le paramétrage SVP."));
            //recherche les demandes de reponse favorable
            //Revoir la requete pour prendre en compte celles qui ne doivent plus etre modifiable(par elaboration)
            List<Demande> demandes = demandeRepository.findToPrintCA(typeDemande.getId(), concerneStructureId, annee);
            //recherche des visas
            List<TypeVisa> typeVisas = typeVisaRepository.findByTypeDemandeIdOrderByNumeroOrdreAsc(typeDemande.getId());
            if (typeVisas == null || CollectionUtils.isEmpty(typeVisas)) {
                throw new CustomException("Visas spécifiques inexistants. Veuillez contacter l'admin pour le paramétrage SVP.");
            }
            //recherche des articles 
            List<ArticleTypeDemande> articleTypeDemandes = articleTypeDemandeRepository.findByTypeDemandeIdOrderByNumeroOrdreAsc(typeDemande.getId());
            if (articleTypeDemandes == null || CollectionUtils.isEmpty(articleTypeDemandes)) {
                throw new CustomException("Articles spécifiques inexistants. Veuillez contacter l'admin pour le paramétrage SVP.");
            }
            // chargement du logo
            InputStream logoStream = AppUtil.getAppDefaultLogo();

            //liste des visas concernes
            List<VisaRE> visaREs = new ArrayList<>();
            for (TypeVisa typeVisa : typeVisas) {
                visaREs.add(new VisaRE(typeVisa.getVisa().getLibelle() + " ;"));
            }
            //liste des articles concernes
            List<ArticleRE> articleREs = new ArrayList<>();
            String article1 = "";
            for (int i = 0; i < articleTypeDemandes.size(); i++) {
                if (i == 0) {
                    article1 = articleTypeDemandes.get(i).getArticle().getCode() + " : " + articleTypeDemandes.get(i).getArticle().getAttributLibelle();
                } else {
                    articleREs.add(new ArticleRE(articleTypeDemandes.get(i).getArticle().getCode() + " : " + articleTypeDemandes.get(i).getArticle().getAttributLibelle()));
                }
            }
            //liste des demandeurs concernes
            List<AgentRE> agentREs = new ArrayList<>();
            int ordre = 0;
            for (Demande d : demandes) {

                agentREs.add(new AgentRE("" + (++ordre),
                        d.getAgent().getMatricule(),
                        d.getAgent().getNom() + " " + d.getAgent().getPrenom(),
                        d.getAgent().getCorps().getLibelleCorps(),
                        d.getDebutPeriodeOuvrant() + " au " + d.getFinPeriodeOuvrant(),
                        d.getDebutPeriodeJouissance() + " au " + d.getFinPeriodeJouissance(),
                        d.getLieuJouissanceBF() + " " + d.getLieuJouissanceEtrang()));
            }

            // le titre de l'acte
            String titre = "DECISION N° " + demandes.get(0).getActe().getReference() + "/" + ms.getMinistere().getSigle().toUpperCase()
                    + ((ms.getStructure().getParent() == null)
                       ? "/" + ms.getStructure().getSigle().toUpperCase()
                       : "/" + ms.getStructure().getParent().getSigle().toUpperCase() + "/" + ms.getStructure().getSigle().toUpperCase());

            // conteneur de données de base à imprimer
            CongeAdminDataRE congeAdminDataRE = new CongeAdminDataRE(ms.getMinistere().getLibelle().toUpperCase(),
                    ((ms.getStructure().getParent() == null) ? null : ms.getStructure().getParent().getLibelle().toUpperCase()),
                    ms.getStructure().getLibelle().toUpperCase(), logoStream, titre, visaREs);

            CongeAdminDataNextRE congeAdminDataNextRE = new CongeAdminDataNextRE(article1, articleREs, agentREs, "Hamidou SAWADOGO");

            InputStream inputStream = this.getClass().getResourceAsStream("/conge_administratif.jasper");
            InputStream inputStream1 = this.getClass().getResourceAsStream("/conge_administratif_next.jasper");

            JRDataSource jRDataSource = new JRBeanCollectionDataSource(Arrays.asList(congeAdminDataRE));
            JRDataSource jRDataSource1 = new JRBeanCollectionDataSource(Arrays.asList(congeAdminDataNextRE));

            Map<String, Object> map = new HashMap<>();
            Map<String, Object> map1 = new HashMap<>();

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(inputStream);
            JasperReport japerReport1 = (JasperReport) JRLoader.loadObject(inputStream1);

            JasperPrint jaspertPrint = JasperFillManager.fillReport(japerReport, map, jRDataSource);
            JasperPrint jaspertPrint1 = JasperFillManager.fillReport(japerReport1, map1, jRDataSource1);

            // concatenation des pages  
            List pges = jaspertPrint1.getPages();
            for (int k = 0; k < pges.size(); k++) {
                JRPrintPage jp = (JRPrintPage) pges.get(k);
                jaspertPrint.addPage(jp);
            }

            JasperExportManager.exportReportToPdfStream(jaspertPrint, outputStream);

        } catch (JRException e) {
            log.error("Erreur survenue lors de la génération de l'acte : {}", e);
            throw new CustomException("Document indisponible, pour cause d'erreur inconnue ! Veuillez réessayer SVP.");
        }
    }

}
