package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.AgentSolde;
import bf.mfptps.appgestionsconges.entities.AgentStructure;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Privilege;
import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.enums.EStatusActe;
import bf.mfptps.appgestionsconges.repositories.ActeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.AgentSoldeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentStructureRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeActeRepository;
import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.CustomException;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.mapper.ActeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.utils.WordReplacer;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import net.minidev.json.JSONObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Service Implementation for managing {@link Privilege}.
 */
@Service
@Transactional
public class ActeServiceImpl implements ActeService {

    private final Logger log = LoggerFactory.getLogger(ActeServiceImpl.class);

    private final ActeRepository acteRepository;
    private final AgentStructureRepository agentStructureRepository;
    private final AgentSoldeRepository agentSoldeRepository;
    private final AgentRepository agentRepository;
    private final DemandeRepository demandeRepository;
    private final TypeActeRepository typeActeRepository;
    private final ActeMapper acteMapper;

    public ActeServiceImpl(ActeRepository acteRepository, ActeMapper acteMapper, AgentStructureRepository agentStructureRepository, AgentSoldeRepository agentSoldeRepository, DemandeRepository demandeRepository, TypeActeRepository typeActeRepository, AgentRepository agentRepository) {
        this.acteRepository = acteRepository;
        this.agentStructureRepository = agentStructureRepository;
        this.agentSoldeRepository = agentSoldeRepository;
        this.agentRepository = agentRepository;
        this.demandeRepository = demandeRepository;
        this.typeActeRepository = typeActeRepository;
        this.acteMapper = acteMapper;
    }

    @Override
    public ActeDTO create(ActeDTO acteDTO) {
        log.debug("Request to save Privilege : {}", acteDTO);
        Acte acte = acteMapper.toEntity(acteDTO);
        if (null == acte.getTypeActe()) {
            log.error("Type acte ne peut pas etre null");
            throw new CustomException("Type acte ne peut pas etre null");
        }
        if (null == acte.getDemandes() || acte.getDemandes().isEmpty()) {
            log.error("Aucune demande selectionnee pour la create de l'acte");
            throw new CustomException("Aucune demande selectionnee pour la create de l'acte");
        }

        TypeActe typeActe = typeActeRepository.findByReference(acte.getTypeActe().getReference()).orElseThrow(() -> new CustomException("Le type d'acte n'existe pas ou a ete supprime"));
        Set<Demande> acteDemandes = new HashSet<Demande>();
        acte.getDemandes().stream().forEach((demande) -> {
            Demande demandeFromDb = demandeRepository.findByNumeroDemande(demande.getNumeroDemande())
                    .orElseThrow(() -> new CustomException("La demande portant le numero [" + demande.getNumeroDemande() + "] n'existe pas ou a ete supprime"));

            demandeFromDb.setActe(acte);
            acteDemandes.add(demandeFromDb);

        });
        acte.setDemandes(acteDemandes);
        acte.setTypeActe(typeActe);
        return acteMapper.toDto(acteRepository.save(acte));
    }

    @Override
    public ActeDTO update(ActeDTO acteDTO) {
        log.debug("Request to update acte : {}", acteDTO);
        Acte acte = acteMapper.toEntity(acteDTO);
        if (null == acte.getId() || !acteRepository.existsById(acte.getId())) {
            throw new CustomException("L'acte en cours de mise a jour, n'existe pas ou a ete supprime");
        }
        return acteMapper.toDto(acteRepository.save(acte));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Acte> findAll(Pageable pageable) {
        log.debug("Request to get all Privilege");
        return acteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Acte> findOne(Long id) {
        log.debug("Request to get acte : {}", id);
        return acteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete acte : {}", id);
        acteRepository.deleteById(id);
    }

    @Override
    public File generateActe(String referenceActe) {
        if (!StringUtils.hasText(referenceActe)) {
            throw new CustomException("La reference de l'acte ne peux pas etre null");
        }

        Acte acteTogenerate = acteRepository.findByReference(referenceActe).orElseThrow(() -> new CustomException("L'acte portant la reference [" + referenceActe + "] n'existe pas ou a ete supprime"));

        TypeActe typeActe = acteTogenerate.getTypeActe();
        if (null == typeActe) {
            throw new CustomException("Le type de l'acte en cours de generation n'a pas ete definie");
        }

        if (null == acteTogenerate.getDemandes() || acteTogenerate.getDemandes().isEmpty()) {
            throw new CustomException("Demande non definie dans l'acte [" + referenceActe + "]");
        }

        try {
            log.debug("TEMPLATE URI {}", typeActe.getTemplateUri());
            InputStream in = new FileInputStream(new File(typeActe.getTemplateUri()));
            XWPFDocument document = new XWPFDocument(in);

            File resultFile = null;
            switch (typeActe.getPorteActe()) {
                case INDIVIDUEL:
                    resultFile = generate_individual_acte(document, acteTogenerate);
                    break;

                case GROUPE:
                    resultFile = generate_groupe_acte(document, acteTogenerate);
                default:
                    throw new CustomException("Portee du type d'acte non reconnue");
            }

            return resultFile;
        } catch (Exception e) {
            e.printStackTrace();

            log.debug("TEMPLATE URI {}", typeActe.getTemplateUri());
            throw new CustomException("Echec lors de la generation du fichier pdf de l'acte [" + acteTogenerate.getReference() + "]");
        }

    }

    private File generate_individual_acte(XWPFDocument document, Acte acte) throws XmlException {

        /*if( !document.getTables().isEmpty()) {
			throw new CustomException("Type de document exemple non definie pour un acte individuel");
		}*/
        String pattern = "dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));

        List<Demande> demandes = new ArrayList<>(acte.getDemandes());
        if (demandes.size() > 1) {
            throw new CustomException("Le nombre( " + demandes.size() + ") de demande n'est pas supporte pour ce type d'acte.");
        }
        Demande demande = demandes.get(0);
        Agent agent = demande.getAgent();

        AgentStructure agentstruct = agentStructureRepository.findOneByAgentMatriculeOrAgentEmail(agent.getMatricule(), agent.getEmail());

        AgentSolde agentSolde = agentSoldeRepository.findUserSoldeByYear(agent.getMatricule(), AppUtil.getCurrentYear(), demande.getTypeDemande().getCode())
                .orElseThrow(() -> new CustomException("Echec lors de la recuperation du solde restant de l'agent"));

        String qrCodeResponsableAgent = "";
        Agent responsableAgent = agentRepository.findAgentSystemByMatricule(agent.getMatricule());
        if (null != responsableAgent) {
            String nomPrenom = "" + responsableAgent.getNom() + (StringUtils.hasText(responsableAgent.getNomJeuneFille()) ? "/" + responsableAgent.getNomJeuneFille() : "") + " " + responsableAgent.getPrenom();
            qrCodeResponsableAgent = nomPrenom + "\n" + responsableAgent.getQualite();
        }

        if (null == agentstruct) {
            throw new CustomException("Failed to get the structure of agent");
        }
        JSONObject keysValues = new JSONObject();

        keysValues.put("$ENTETE_MINISTERE$", acte.getEnteteMinistere());
        keysValues.put("$ENTETE_STRUCTURE$", acte.getEnteteStructure());
        keysValues.put("$REFERENCE_ACTE$", acte.getReference());
        keysValues.put("$DATE_ACTE$", simpleDateFormat.format(new Date()));
        long soldePris = demande.getTypeDemande().getSoldeAnnuel() - agentSolde.getSoldeRestant();
        keysValues.put("$SOLDEPRIS$", "" + soldePris);
        keysValues.put("$SOLDERESTE$", "" + agentSolde.getSoldeRestant());
        Long hours = demande.getDureeAbsence() * 24;
        keysValues.put("$TOTALHEURES$", "" + hours);
        String nomPrenom = "" + agent.getNom() + (StringUtils.hasText(agent.getNomJeuneFille()) ? "/" + agent.getNomJeuneFille() : "") + " " + agent.getPrenom();
        keysValues.put("$NOM_PRENOM_AGENT$", nomPrenom);
        keysValues.put("$MATRICULE_AGENT$", agent.getMatricule());
        keysValues.put("$FONCTION_AGENT$", agent.getQualite());
        keysValues.put("$DATE_DEBUT$", simpleDateFormat.format(demande.getPeriodeDebut()));
        keysValues.put("$DATE_FIN$", simpleDateFormat.format(demande.getPeriodeFin()));
        String motifAbsence = null != demande.getMotifAbsence() ? demande.getMotifAbsence().getLibelle() : "";
        keysValues.put("$MOTIF_ABSENCE$", motifAbsence);
        keysValues.put("$TYPE_DEMANDE$", (demande.getTypeDemande() != null ? demande.getTypeDemande().getLibelle().toLowerCase() : "autorisation d’absence"));
        keysValues.put("$ANNEE$", acte.getAnnee());
        keysValues.put("$RESPONSABLE$", acte.getNomPrenomCreator());
        keysValues.put("$TITRE_RESPONSABLE$", acte.getTitreCreator());
        keysValues.put("$AMPLIATION$", acte.getAmpliation());
        String structureAgent = agentstruct.getStructure() != null ? agentstruct.getStructure().getLibelle() : "";
        keysValues.put("$STRUCTURE_AGENT$", structureAgent);

        try {
            return new WordReplacer().processDOCXWordReplace(acte.getTypeActe().getTemplateUri(), acte.getReference(), qrCodeResponsableAgent, keysValues);
        } catch (Exception e) {
            log.error("Failed to generate acte doc format", e);
            throw new CustomException("Failed to generate acte from XWPFConverterException format");
        }
    }

    private File generate_groupe_acte(XWPFDocument document, Acte acte) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        if (document.getTables().isEmpty()) {
            throw new CustomException("Type de document exemple non definie pour un acte de groupe");
        }

        return null;
    }

    @Transactional
    @Override
    public Acte validerActeCA(Long id) {
        Acte acte = acteRepository.findById(id).orElseThrow(() -> new CustomException("Acte inexistant."));
        List<Demande> demandes = demandeRepository.findByActeId(acte.getId());
        try {
            for (Demande demande : demandes) {
                demande.setElabore(true);
            }
            demandeRepository.saveAll(demandes);
            acte.setStatus(EStatusActe.VALIDE);
            acte = acteRepository.save(acte);
            return acte;
        } catch (Exception e) {
            throw new CustomException("Erreur inconnue ! Veuillez réessayer SVP.");
        }
    }

}
