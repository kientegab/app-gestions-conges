package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.*;
import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.EStatusActe;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import bf.mfptps.appgestionsconges.enums.ETypeValidation;
import bf.mfptps.appgestionsconges.repositories.ActeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.AgentSoldeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentStructureRepository;
import bf.mfptps.appgestionsconges.repositories.AvisRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.service.AgentService;
import bf.mfptps.appgestionsconges.service.CustomException;
import bf.mfptps.appgestionsconges.service.DemandeService;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.ValidationDTO;
import bf.mfptps.appgestionsconges.service.mapper.DemandeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

    String CONGE = "CONGE_ANNUEL";
    String JOUISSANCE_ANNUEL = "JOUISS_ANNUEL";
    String AUTORISATION_ABSENCE = "AUTRE_ABSENCE";

    private static final String DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR = "La demande n'a pas encore recu l'avis du superieur !!!";

    private static final String REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL = "La refrenece de la demande ne peut pas etre null!!!";

    private final Logger log = LoggerFactory.getLogger(DemandeServiceImpl.class);

    private final DemandeRepository demandeRepository;
    private final ActeRepository acteRepository;
    private final AgentRepository agentRepository;
    private final TypeDemandeRepository typeDemandeRepository;
    private final AgentSoldeRepository agentSoldeRepository;
    private final AvisRepository avisRepository;
    private final AgentStructureRepository agentStructureRepository;
    private final DemandeMapper demandeMapper;
    private final ApplicationProperties applicationProperties;

    @Autowired
    private AgentService agentService;

    public DemandeServiceImpl(DemandeRepository demandeRepository, ActeRepository acteRepository, DemandeMapper demandeMapper,
            AgentRepository agentRepository, TypeDemandeRepository typeDemandeRepository, ApplicationProperties applicationProperties, AgentSoldeRepository agentSoldeRepository, AgentStructureRepository agentStructureRepository, AvisRepository avisRepository) {
        this.demandeRepository = demandeRepository;
        this.acteRepository = acteRepository;
        this.agentRepository = agentRepository;
        this.typeDemandeRepository = typeDemandeRepository;
        this.agentSoldeRepository = agentSoldeRepository;
        this.avisRepository = avisRepository;
        this.agentStructureRepository = agentStructureRepository;
        this.demandeMapper = demandeMapper;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public DemandeDTO create(DemandeDTO demandeDTO, MultipartFile[] fichiersJoint) {
        Demande demande = demandeMapper.toEntity(demandeDTO);
        String userStorage = "";

        if (null == demande.getAgent()) {
            throw new CustomException("L'utilisateur éffectuant la demande ne peut pas être null");
        }
        Agent agent = agentRepository.findByMatricule(demande.getAgent().getMatricule())
                .orElseThrow(() -> new CustomException("L'agent qui effectue la demande est inexistant!!!"));
        demande.setAgent(agent);
        userStorage = agent.getMatricule();

        AgentStructure agentStructure = agentStructureRepository.findOneByAgentMatriculeOrAgentEmail(agent.getMatricule(), agent.getEmail());

        TypeDemande typeDemande = demande.getTypeDemande();

        if (null == agentStructure || null == agentStructure.getStructure()) {
            throw new CustomException("La structure de l'agent  éffectuant la demande ne peut pas être null!!!");
        }

        if (null == typeDemande) {
            throw new CustomException("Le type de la demande ne peut pas être null!!!");
        }

        typeDemande = typeDemandeRepository.findById(demande.getTypeDemande().getId())
                .orElseThrow(() -> new CustomException("Le type de demande est inexistant."));
        demande.setTypeDemande(typeDemande);

        if (typeDemande.getCode().contains("JOUIS") && demande.getMotifAbsence() == null) {
            throw new CustomException("Motif Absence ne peut pas etre null");
        }

        if (typeDemande.getCode().contains("CONG") && !StringUtils.hasText(demande.getRefLastDecision())) {
            throw new CustomException("La reference de la derniere decision ne peut pas etre null");
        }
        String refDemande = demandeRefgenerator(agentStructure.getStructure().getSigle());
        demande.setNumeroDemande(refDemande);

        if (null == demande.getPeriodeDebut() || null == demande.getPeriodeFin()) {
            throw new CustomException("Saisir la période de début et la période de fin de la demande.");
        }

        long demandeDays = AppUtil.getDifferenceDays(demande.getPeriodeDebut(), demande.getPeriodeFin());

        Optional<AgentSolde> optionalSolde = agentSoldeRepository.findUserSoldeByYear(agent.getMatricule(), AppUtil.getCurrentYear(), typeDemande.getCode());

        if (optionalSolde.isPresent()) {
            if (demandeDays > optionalSolde.get().getSoldeRestant()) {
                throw new CustomException("Vous avez atteint votre solde de demande de type [" + typeDemande.getLibelle() + "]");
            }
        } else {
            AgentSolde solde = new AgentSolde();
            solde.setAnnee(AppUtil.getCurrentYear());
            solde.setMatricule(agent.getMatricule());
            solde.setTypeDemande(typeDemande.getCode());
            solde.setSoldeRestant(typeDemande.getSoldeAnnuel());
            agentSoldeRepository.save(solde);
            demande.setDureeAbsence(demandeDays);
        }
        if (fichiersJoint != null && fichiersJoint.length > 0) {
            Set<Document> documents = new HashSet<Document>();
            for (MultipartFile file : fichiersJoint) {
                if (!file.isEmpty()) {
                    try {
                        String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), userStorage, file);
                        Document document = new Document();
                        document.setDemande(demande);
                        document.setUrl(fileUri);
                        document.setReference(refDemande + "_DOC-" + System.currentTimeMillis());
                        document.setNomDocument(file.getName());
                        documents.add(document);
                    } catch (Exception e) {
                        log.error("Failed to save file on server", e);
                        throw new CustomException("Echec lors de l'enregistrement du fichier sur le server : " + e.getMessage());
                    }
                }
            }
            demande.setDocuments(documents);
        }

        demande.setStatusDemande(EStatusDemande.INITIATION);
        demande.setPositionDemande(EPositionDemande.DEMANDEUR);
        return demandeMapper.toDto(demandeRepository.save(demande));
    }

    @Override
    public DemandeDTO update(DemandeDTO demandeDTO) {
        Demande demande = demandeMapper.toEntity(demandeDTO);
        return demandeMapper.toDto(demandeRepository.save(demande));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demande> get(String numeroDemande) {
        return demandeRepository.findByNumeroDemande(numeroDemande);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDTO> findAll(Pageable pageable) {
        return demandeRepository.findAll(pageable).map(demandeMapper::toDto);
    }

    @Override
    public void delete(Long code) {
        demandeRepository.deleteById(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demande> get(Long id) {
        return demandeRepository.findById(id);
    }

    private String demandeRefgenerator(String userStructureSigle) {
        //	Long countDemande = demandeRepository.countStructureDemande(userStructureSigle);

        Long countDemande = demandeRepository.count();

        return userStructureSigle + "" + String.format("%04d", countDemande++);
    }

    @Override
    public DemandeDTO validation_sh(ValidationDTO validationDTO, boolean islastValidationNode) {

        if (!StringUtils.hasText(validationDTO.getNumeroDemande())) {
            throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
        }

        Demande demande = demandeRepository.findByNumeroDemande(validationDTO.getNumeroDemande()).orElseThrow(()
                -> new CustomException("La demande portant la reference [" + validationDTO.getNumeroDemande() + "] n'existe pas dans le syteme"));

        if (!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
            throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
        }
        Agent connectedAgent = agentRepository.findByMatricule(validationDTO.getMatriculeValidateur()).orElseThrow(() -> new CustomException("L'agent portant le matricule [" + validationDTO.getMatriculeValidateur() + "] n'existe pas!!"));
        Agent demandeur = demande.getAgent();
        Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());
        Avis avis = new Avis();

        if (!demandeur.getMatriculeResp().equalsIgnoreCase(connectedAgent.getMatricule())) {
            throw new CustomException("Seul le supérieur hiérarchique de demandeur peut éffectuer cette action!!!");
        }

        if (optionalAvis.isPresent()) {
            avis = optionalAvis.get();
            if (EStatusDemande.VALIDE.equals(avis.getDemande().getStatusDemande())) {
                throw new CustomException("La demande portant le numero [" + validationDTO.getNumeroDemande() + "] à déjà été validé!!!");
            }
        } else {
            avis.setDemande(demande);
        }

        if (ETypeValidation.APPROVED.equals(validationDTO.getEnumValidation())) {
            String typeDemandeCode = avis.getDemande().getTypeDemande().getCode();
            if (typeDemandeCode.contains("JOUISS") || typeDemandeCode.contains("AUTRE")) {
                avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
                updateUserSolde(avis);
            }
        } else {
            avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
            avis.getDemande().setMotifRejet(validationDTO.getAvis());
        }
        avis.setAvisSH(validationDTO.getAvis());
        avis.getDemande().setPositionDemande(EPositionDemande.SH);
        avis.setDateAvisSH(new Date());
        try {
            avisRepository.save(avis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Failde to save avis");
        }

        return demandeMapper.toDto(demande);
    }

    @Override
    public DemandeDTO validation_sg(ValidationDTO validationDTO, boolean islastValidationNode) {
        if (!StringUtils.hasText(validationDTO.getNumeroDemande())) {
            throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
        }

        if (!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
            throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
        }

        Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());

        if (!optionalAvis.isPresent()) {
            throw new CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
        }
        Avis avis = optionalAvis.get();
        if (ETypeValidation.REJECTED.equals(validationDTO.getEnumValidation())) {
            avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
            avis.getDemande().setMotifRejet(validationDTO.getAvis());
        }

        if (islastValidationNode) {
            avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
            updateUserSolde(avis);
        }

        avis.setAvisSG(validationDTO.getAvis());
        avis.getDemande().setPositionDemande(EPositionDemande.SG);
        avis.setDateAvisSG(new Date());
        avisRepository.save(avis);

        return demandeMapper.toDto(avis.getDemande());
    }

    @Override
    public DemandeDTO validation_dg(ValidationDTO validationDTO, boolean islastValidationNode) {
        if (!StringUtils.hasText(validationDTO.getNumeroDemande())) {
            throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
        }

        if (!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
            throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
        }
        Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());

        if (!optionalAvis.isPresent()) {
            throw new CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
        }
        Avis avis = optionalAvis.get();
        if (ETypeValidation.REJECTED.equals(validationDTO.getEnumValidation())) {
            avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
            avis.getDemande().setMotifRejet(validationDTO.getAvis());
        }

        if (islastValidationNode) {
            avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
            updateUserSolde(avis);
        }

        avis.setAvisDG(validationDTO.getAvis());
        avis.getDemande().setPositionDemande(EPositionDemande.DGFP);
        avis.setDateAvisDG(new Date());
        avisRepository.save(avis);

        return demandeMapper.toDto(avis.getDemande());
    }

    @Override
    public DemandeDTO validation_drh(ValidationDTO validationDTO, boolean islastValidationNode) {
        if (!StringUtils.hasText(validationDTO.getNumeroDemande())) {
            throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
        }

        if (!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
            throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
        }
        Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());

        if (!optionalAvis.isPresent()) {
            throw new CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
        }
        Avis avis = optionalAvis.get();

        if (ETypeValidation.REJECTED.equals(validationDTO.getEnumValidation())) {
            avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
            avis.getDemande().setMotifRejet(validationDTO.getAvis());
        }

        if (islastValidationNode) {
            avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
            updateUserSolde(avis);
        }

        avis.setAvisDRH(validationDTO.getAvis());
        avis.getDemande().setPositionDemande(EPositionDemande.DRH);
        avis.setDateAvisDRH(new Date());
        avisRepository.save(avis);

        return demandeMapper.toDto(avis.getDemande());
    }

    @Override
    public Page<DemandeDTO> getAbsenceByMatricule(String matricule, Pageable pageable) {
        return demandeRepository.findAllByAgentMatriculeAndTypeDemandeCode(matricule, AUTORISATION_ABSENCE, pageable).map(demandeMapper::toDto);
    }

    @Override
    public Page<DemandeDTO> getAbsenceByStructure(Long structureId, Pageable pageable) {
        List<AgentStructure> agentStructureList = agentStructureRepository.findAllByStructureIdAndActifTrue(structureId);
        List<Agent> agents = agentService.listAgentParStructure(agentStructureList);
        Page<DemandeDTO> demandes;
        List<DemandeDTO> demandes1 = new ArrayList<>();
        for (Agent agent : agents) {
            List<Demande> demandeList = new ArrayList<>();
            demandeList = demandeRepository.findAllByAgentAndTypeDemandeCode(agent, AUTORISATION_ABSENCE);
            for (Demande demande : demandeList) {
                demandes1.add(demandeMapper.toDto(demande));
            }
        }
        demandes = new PageImpl<>(demandes1);
        return demandes;
    }

    @Override
    public Page<DemandeDTO> getCongeByMatricule(String matricule, Pageable pageable) {
        return demandeRepository.findAllByAgentMatriculeAndTypeDemandeCode(matricule, CONGE, pageable).map(demandeMapper::toDto);

    }

    @Override
    public Page<DemandeDTO> getCongeByStructure(Long structureId, Pageable pageable) {
        List<AgentStructure> agentStructureList = agentStructureRepository.findAllByStructureIdAndActifTrue(structureId);
        List<Agent> agents = agentService.listAgentParStructure(agentStructureList);
        Page<DemandeDTO> demandes;
        List<DemandeDTO> demandes1 = new ArrayList<>();
        for (Agent agent : agents) {
            List<Demande> demandeList = new ArrayList<>();
            demandeList = demandeRepository.findAllByAgentAndTypeDemandeCode(agent, CONGE);
            for (Demande demande : demandeList) {
                demandes1.add(demandeMapper.toDto(demande));
            }
        }
        demandes = new PageImpl<>(demandes1);
        return demandes;
    }

    @Override
    public Page<DemandeDTO> getJouissanceByStructure(Long structureId, Pageable pageable) {
        List<AgentStructure> agentStructureList = agentStructureRepository.findAllByStructureIdAndActifTrue(structureId);
        List<Agent> agents = agentService.listAgentParStructure(agentStructureList);
        Page<DemandeDTO> demandes;
        List<DemandeDTO> demandes1 = new ArrayList<>();
        for (Agent agent : agents) {
            List<Demande> demandeList = new ArrayList<>();
            demandeList = demandeRepository.findAllByAgentAndTypeDemandeCode(agent, JOUISSANCE_ANNUEL);
            for (Demande demande : demandeList) {
                demandes1.add(demandeMapper.toDto(demande));
            }
        }
        demandes = new PageImpl<>(demandes1);
        return demandes;
    }

    @Override
    public Page<DemandeDTO> getDemandesValid(String codeTypeDmd, Pageable pageable) {
        return demandeRepository.findAllByTypeDemandeCode(codeTypeDmd, pageable).map(demandeMapper::toDto);
    }

    private void updateUserSolde(Avis avis) {
        AgentSolde agentSolde = agentSoldeRepository
                .findUserSoldeByYear(avis.getDemande().getAgent().getMatricule(), AppUtil.getCurrentYear(),
                        avis.getDemande().getTypeDemande().getCode()).orElseThrow(() -> new CustomException("Echec lors de la recuperation du solde annuel de l'agent"));
        log.error("UPDATE SOLDE   ");
        long nobreRestant = agentSolde.getSoldeRestant() - avis.getDemande().getDureeAbsence();
        agentSoldeRepository.updateUserSolde(agentSolde.getId(), nobreRestant);
    }

    /**
     * PROCESS DEMANDE DE DECISION DE CONGE ANNUEL, DE VALIDATIONS,
     * D'ELABORATION D'ACTE DE DECISION, D'EXPORT DE DECISION DE CONGE
     *
     * (1) la demande est effecutée par un demandeur.
     *
     * (2) cette demande fait l'objet de validations SH_INITIATION && SG_VALIDE.
     *
     * (3) lister les demandes de decision de telle année, de positionDemande =
     * SG et statusDemande = VALIDE pour permettre aux redacteurs d'élaborer les
     * peériodes ouvrants de chaque demande.
     *
     * (4) à la phase d'elaboration, si pour chaque demande la positionDemande =
     * SG et statusDemande = VALIDER, alors calculer et stocker la referenceActe
     * et anneeActe dans une nouvelle ligne Acte et mettre statusActe à
     * INITIATION. L'idActe de cette nouvelle ligne doit etre updaté dans les
     * demandes concernées. L'élaboration consiste à updater les colonnes
     * debutPeriodeOuvrant, finPeriodeOuvrant, à calculer auto et updater les
     * debutPeriodeJouissance et debutPeriodeJouissance.
     *
     * (5) valider acte (apres quoi, on ne peut plus modifier des dates lors
     * d'une élaboration).
     */
    @Override
    public Page<Demande> findCAByAnneeAndSGValidated(Long idStructure, Integer annee, Long idTypedemande, Pageable pageable) {
        return demandeRepository.findCAByAnneeAndSGValidated(idStructure, annee, idTypedemande, EStatusDemande.VALIDE, EPositionDemande.SG, pageable);
    }

    /**
     *
     * debutPeriodeJouissance = finPeriodeOuvrant + 1
     *
     * finPeriodeJouissance = debutPeriodeJouissance + 30
     *
     * @param demandes
     * @return
     */
    @Transactional(rollbackFor = CustomException.class)
    @Override
    public String elaborerDecisionCA(List<Demande> demandes) {
        //verifications et levees d'exceptions
        for (Demande d : demandes) {
            if (d.getNumStructure() != demandes.get(0).getNumStructure()) { //traiter les lots de demandes par strucutre
                throw new CustomException("Les auteurs de ces demandes ne relèvent pas tous de la même structure. Veuillez traiter par structure SVP.");
            }
            if (d.getPositionDemande() != EPositionDemande.SG || d.getStatusDemande() != EStatusDemande.VALIDE) {
                throw new CustomException("La demande [" + d.getNumeroDemande() + "] n'a pas suivi toutes les procédures nécéssaires pour l'élaboration.");
            }
            if (d.getDebutPeriodeOuvrant() == null || d.getFinPeriodeOuvrant() == null) {
                throw new CustomException("Veuillez reprendre l'élaboration en renseignant toutes les dates requises SVP.");
            }
            if (d.isElabore()) {
                throw new CustomException("Impossible d'élaborer un acte déjà validé.");
            }
        }

        Long count = acteRepository.count();

        Acte acte = new Acte();
        Calendar calendarToYear = Calendar.getInstance();
        calendarToYear.setTime(demandes.get(0).getDebutPeriodeOuvrant());
        acte.setAnnee("" + calendarToYear.get(Calendar.YEAR));
        acte.setStatus(EStatusActe.INITIATION);
        acte.setReference(acte.getAnnee() + "-" + String.format("%05d", ++count));//formule : année + codeMinistere + numeroOrdreActe
        acte = acteRepository.save(acte);

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();

        List<Demande> demandesToSave = new ArrayList<>();
        for (Demande demande : demandes) {
            calendar.setTime(demande.getFinPeriodeOuvrant());
            calendar.add(Calendar.DATE, 1);
            demande.setDebutPeriodeJouissance(calendar.getTime());

            calendar1.setTime(demande.getDebutPeriodeJouissance());
            calendar1.add(Calendar.DATE, 30);
            demande.setFinPeriodeJouissance(calendar1.getTime());
            demande.setActe(acte);

            demandesToSave.add(demande);
        }
        demandeRepository.saveAll(demandesToSave);

        return "Elaboration effectuée avec succès";
    }
}
