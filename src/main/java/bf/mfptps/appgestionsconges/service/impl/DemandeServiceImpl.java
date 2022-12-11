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
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.config.ApplicationProperties;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Document;
import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.Utilisateur;
import bf.mfptps.appgestionsconges.entities.UtilisateurSolde;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeDemandeRepository;
import bf.mfptps.appgestionsconges.repositories.UserSoldeRepository;
import bf.mfptps.appgestionsconges.repositories.UtilisateurRepository;
import bf.mfptps.appgestionsconges.service.DemandeService;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
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

	private final Logger log = LoggerFactory.getLogger(DemandeServiceImpl.class);

	private final DemandeRepository demandeRepository;
	private final UtilisateurRepository utilisateurRepository;
	private final TypeDemandeRepository typeDemandeRepository;
	private final UserSoldeRepository   userSoldeRepository;
	private final DemandeMapper demandeMapper;
	private final ApplicationProperties applicationProperties;

	public DemandeServiceImpl(DemandeRepository demandeRepository, DemandeMapper demandeMapper,
			UtilisateurRepository utilisateurRepository, TypeDemandeRepository typeDemandeRepository, ApplicationProperties applicationProperties, UserSoldeRepository userSoldeRepository) {
		this.demandeRepository = demandeRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.typeDemandeRepository = typeDemandeRepository;
		this.userSoldeRepository = userSoldeRepository;
		this.demandeMapper = demandeMapper;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public DemandeDTO create(DemandeDTO demandeDTO, MultipartFile[] fichiersJoint) {
		Demande demande = demandeMapper.toEntity(demandeDTO);
		String userStorage = "";
		
		if(null == demande.getUtilisateur()) {
			throw new CustomException("L'utilisateur éffectuant la demande ne peut pas être null");
		}
		Utilisateur user = utilisateurRepository.findByMatricule(demande.getUtilisateur().getMatricule())
				.orElseThrow(() -> new CustomException("L'utilisateur qui effectue la demande est inexistant!!!"));
		demande.setUtilisateur(user);
		userStorage = user.getMatricule();
		Structure userStructure = user.getStructure();
		TypeDemande typeDemande = demande.getTypeDemande();
		
		
		if(null== userStructure) {
			throw new CustomException("La structure de l'utilisateur  éffectuant la demande ne peut pas être null!!!");
		}
		
		if(null== typeDemande) {
			throw new CustomException("Le type de la demande ne peut pas être null!!!");
		}
		
		 typeDemande = typeDemandeRepository.findById(demande.getTypeDemande().getId())
					.orElseThrow(() -> new CustomException("Le type de demande est inexistant."));
			demande.setTypeDemande(typeDemande);
			
			
		
		String refDemande = demandeRefgenerator(userStructure.getSigle());
		demande.setNumeroDemande(refDemande);
		
		if(null== demande.getPeriodeDebut() || null == demande.getPeriodeFin()) {
			throw new CustomException("Saisir la période de début et la période de fin de la demande.");
		}
		
		long demandeDays = AppUtil.getDifferenceDays(demande.getPeriodeDebut(), demande.getPeriodeFin());
		
		Optional<UtilisateurSolde> optionalSolde = userSoldeRepository.findUserSoldeByYear(user.getMatricule(), AppUtil.getCurrentYear(), typeDemande.getCode());

		if(optionalSolde.isPresent()) {
			if(demande.getDureeAbsence()> optionalSolde.get().getSoldeRestant()) {
				throw new CustomException("Vous avez atteint votre solde de demande de type ["+typeDemande.getLibelle()+"]");
			}
		}else {
			UtilisateurSolde solde = new UtilisateurSolde();
			solde.setAnnee(AppUtil.getCurrentYear());
			solde.setMatricule(user.getMatricule());
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
		Long countDemande = demandeRepository.countStructureDemande(userStructureSigle);

		return userStructureSigle + ""+  String.format("%04d", countDemande++) ;
	}

}
