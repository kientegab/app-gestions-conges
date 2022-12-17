package bf.mfptps.appgestionsconges.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.AgentStructure;
import bf.mfptps.appgestionsconges.entities.Avis;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.UtilisateurSolde;
import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import bf.mfptps.appgestionsconges.enums.ETypeValidation;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.AgentStructureRepository;
import bf.mfptps.appgestionsconges.repositories.AvisRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.repositories.UserSoldeRepository;
import bf.mfptps.appgestionsconges.service.DemandeService;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.ValidationDTO;
import bf.mfptps.appgestionsconges.service.mapper.DemandeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

	private static final String DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR = "La demande n'a pas encore recu l'avis du superieur !!!";

	private static final String REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL = "La refrenece de la demande ne peut pas etre null!!!";

	private final Logger log = LoggerFactory.getLogger(DemandeServiceImpl.class);

	private final DemandeRepository demandeRepository;
	private final AgentRepository agentRepository;
	private final TypeDemandeRepository typeDemandeRepository;
	private final UserSoldeRepository   userSoldeRepository;
	private final AvisRepository    avisRepository;
	private final AgentStructureRepository agentStructureRepository;
	private final DemandeMapper demandeMapper;
	private final ApplicationProperties applicationProperties;

	public DemandeServiceImpl(DemandeRepository demandeRepository, DemandeMapper demandeMapper,
			AgentRepository agentRepository, TypeDemandeRepository typeDemandeRepository, ApplicationProperties applicationProperties, UserSoldeRepository userSoldeRepository, AgentStructureRepository agentStructureRepository, AvisRepository avisRepository) {
		this.demandeRepository = demandeRepository;
		this.agentRepository = agentRepository;
		this.typeDemandeRepository = typeDemandeRepository;
		this.userSoldeRepository = userSoldeRepository;
		this.avisRepository = avisRepository;
		this.agentStructureRepository = agentStructureRepository;
		this.demandeMapper = demandeMapper;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public DemandeDTO create(DemandeDTO demandeDTO, MultipartFile[] fichiersJoint) {
		Demande demande = demandeMapper.toEntity(demandeDTO);
		String userStorage = "";

		if(null == demande.getAgent()) {
			throw new CustomException("L'utilisateur éffectuant la demande ne peut pas être null");
		}
		Agent agent = agentRepository.findByMatricule(demande.getAgent().getMatricule())
				.orElseThrow(() -> new CustomException("L'agent qui effectue la demande est inexistant!!!"));
		demande.setAgent(agent);
		userStorage = agent.getMatricule();
		
		AgentStructure agentStructure = agentStructureRepository.findOneByAgentMatriculeOrAgentEmail(agent.getMatricule(), agent.getEmail());
		
		TypeDemande typeDemande = demande.getTypeDemande();


		if(null== agentStructure || null == agentStructure.getStructure()) {
			throw new CustomException("La structure de l'agent  éffectuant la demande ne peut pas être null!!!");
		}

		if(null== typeDemande) {
			throw new CustomException("Le type de la demande ne peut pas être null!!!");
		}

		typeDemande = typeDemandeRepository.findById(demande.getTypeDemande().getId())
				.orElseThrow(() -> new CustomException("Le type de demande est inexistant."));
		demande.setTypeDemande(typeDemande);


		if(typeDemande.getCode().contains("JOUIS") && demande.getMotifAbsence()==null) {
			throw new CustomException("Motif Absence ne peut pas etre null");
		}
		
		if(typeDemande.getCode().contains("CONG") && !StringUtils.hasText(demande.getRefLastDecision())) {
			throw new CustomException("La reference de la derniere decision ne peut pas etre null");
		}
		String refDemande = demandeRefgenerator(agentStructure.getStructure().getSigle());
		demande.setNumeroDemande(refDemande);

		if(null== demande.getPeriodeDebut() || null == demande.getPeriodeFin()) {
			throw new CustomException("Saisir la période de début et la période de fin de la demande.");
		}

		//long demandeDays = AppUtil.getDifferenceDays(demande.getPeriodeDebut(), demande.getPeriodeFin());

		Optional<UtilisateurSolde> optionalSolde = userSoldeRepository.findUserSoldeByYear(agent.getMatricule(), AppUtil.getCurrentYear(), typeDemande.getCode());

		if(optionalSolde.isPresent()) {
			if(demande.getDureeAbsence()> optionalSolde.get().getSoldeRestant()) {
				throw new CustomException("Vous avez atteint votre solde de demande de type ["+typeDemande.getLibelle()+"]");
			}
		}else {
			UtilisateurSolde solde = new UtilisateurSolde();
			solde.setAnnee(AppUtil.getCurrentYear());
			solde.setMatricule(agent.getMatricule());
			solde.setTypeDemande(typeDemande.getCode());
			solde.setSoldeRestant(typeDemande.getSoldeAnnuel());

			userSoldeRepository.save(solde);
		}
		if(fichiersJoint!=null && fichiersJoint.length>0) {
			Set<Document> documents = new HashSet<Document>();
			for(MultipartFile file: fichiersJoint) {
				if(!file.isEmpty()) {
					try {
						String fileUri = AppUtil.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), userStorage, file);
						Document document = new Document();
						document.setDemande(demande);
						document.setUrl(fileUri);
						document.setReference(refDemande+"_DOC-"+ System.currentTimeMillis());
						document.setNomDocument(file.getName());
						documents.add(document);
					} catch (Exception e) {
						log.error("Failed to save file on server", e);
						throw new CustomException("Echec lors de l'enregistrement du fichier sur le server : " +e.getMessage());
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
		return demandeMapper.toDto( demandeRepository.save(demande));
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

		return userStructureSigle + ""+  String.format("%04d", countDemande++) ;
	}


	
	@Override
	public DemandeDTO validation_sh(ValidationDTO validationDTO, boolean islastValidationNode) {
		
		if(!StringUtils.hasText(validationDTO.getNumeroDemande())) {
			throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
		}
		
		Demande demande = demandeRepository.findByNumeroDemande(validationDTO.getNumeroDemande()).orElseThrow(() -> 
		new CustomException("La demande portant la reference ["+ validationDTO.getNumeroDemande()+"] n'existe pas dans le syteme"));
		
		if(!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
			throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
		}
		Agent connectedAgent = agentRepository.findByMatricule(validationDTO.getMatriculeValidateur()).orElseThrow(()-> new CustomException("L'agent portant le matricule ["+ validationDTO.getMatriculeValidateur()+"] n'existe pas!!"));
		Agent demandeur = demande.getAgent();
		Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());
		Avis avis = new Avis();
		
		if(!demandeur.getMatriculeResp().equalsIgnoreCase(connectedAgent.getMatricule())) {
			throw new CustomException("Seul le supérieur hiérarchique de demandeur peut éffectuer cette action!!!");
		}
		
		if(optionalAvis.isPresent()) {
			avis = optionalAvis.get(); 
			if(EStatusDemande.VALIDE.equals(avis.getDemande().getStatusDemande())) {
				throw new CustomException("La demande portant le numero [" + validationDTO.getNumeroDemande() + "] à déjà été validé!!!");
			}
		}else {
			avis.setDemande(demande);
		}
		 
		 
		if(ETypeValidation.APPROVED.equals( validationDTO.getEnumValidation())){
			String typeDemandeCode = avis.getDemande().getNumeroDemande();
			if(typeDemandeCode.contains("JOUISS") || typeDemandeCode.contains("AUTRE")) {
				avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
			}
		}else {
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
		if(!StringUtils.hasText(validationDTO.getNumeroDemande())) {
			throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
		}
		
		if(!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
			throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
		}
		
		Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());
		
		if(!optionalAvis.isPresent()) {
			throw new  CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
		}
		Avis avis = optionalAvis.get();
		if(ETypeValidation.REJECTED.equals( validationDTO.getEnumValidation())){
			avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
			avis.getDemande().setMotifRejet(validationDTO.getAvis());
		}
		
		if(islastValidationNode) {
			avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
		}
		
		avis.setAvisSG(validationDTO.getAvis());
		avis.getDemande().setPositionDemande(EPositionDemande.SG);
		avis.setDateAvisSG(new Date());
		avisRepository.save(avis);
		
		return demandeMapper.toDto( avis.getDemande());
	}
	
	@Override
	public DemandeDTO validation_dg(ValidationDTO validationDTO, boolean islastValidationNode) {
		if(!StringUtils.hasText(validationDTO.getNumeroDemande())) {
			throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
		}
		
		if(!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
			throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
		}
		Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());
		
		if(!optionalAvis.isPresent()) {
			throw new  CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
		}
		Avis avis = optionalAvis.get();
		if(ETypeValidation.REJECTED.equals( validationDTO.getEnumValidation())){
			avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
			avis.getDemande().setMotifRejet(validationDTO.getAvis());
		}
		
		if(islastValidationNode) {
			avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
		}
		
		avis.setAvisDG(validationDTO.getAvis());
		avis.getDemande().setPositionDemande(EPositionDemande.DGFP);
		avis.setDateAvisDG(new Date());
		avisRepository.save(avis);
		
		return demandeMapper.toDto( avis.getDemande());
	}
	
	@Override
	public DemandeDTO validation_drh(ValidationDTO validationDTO, boolean islastValidationNode) {
		if(!StringUtils.hasText(validationDTO.getNumeroDemande())) {
			throw new CustomException(REFRENECE_DE_LA_DEMANDE_NE_PEUT_PAS_ETRE_NULL);
		}
		
	
		if(!StringUtils.hasText(validationDTO.getMatriculeValidateur())) {
			throw new CustomException("Le matricule de l'utilisateur en cours  ne peut pas etre null!!!");
		}
		Optional<Avis> optionalAvis = avisRepository.findAvisByNumeroDemande(validationDTO.getNumeroDemande());
		
		if(!optionalAvis.isPresent()) {
			throw new  CustomException(DEMANDE_N_A_PAS_ENCORE_RECU_AVIS_DU_SUPERIEUR);
		}
		Avis avis = optionalAvis.get();
		
		if(ETypeValidation.REJECTED.equals( validationDTO.getEnumValidation())){
			avis.getDemande().setStatusDemande(EStatusDemande.REJETE);
			avis.getDemande().setMotifRejet(validationDTO.getAvis());
		}
		
		if(islastValidationNode) {
			avis.getDemande().setStatusDemande(EStatusDemande.VALIDE);
		}
		
		avis.setAvisDRH(validationDTO.getAvis());
		avis.getDemande().setPositionDemande(EPositionDemande.DRH);
		avis.setDateAvisDRH(new Date());
		avisRepository.save(avis);
		
		return demandeMapper.toDto( avis.getDemande());
	}

}
